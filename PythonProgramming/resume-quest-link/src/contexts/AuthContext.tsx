import { createContext, useContext, useEffect, useState, ReactNode } from 'react';
import { User, Session } from '@supabase/supabase-js';
import { supabase } from '@/integrations/supabase/client';
import type { Tables } from '@/integrations/supabase/types';

type Profile = Tables<'profiles'>;

interface AuthContextType {
  user: User | null;
  session: Session | null;
  profile: Profile | null;
  loading: boolean;
  signUp: (email: string, password: string, fullName: string, role: 'employer' | 'job_seeker') => Promise<void>;
  signIn: (email: string, password: string) => Promise<void>;
  signOut: () => Promise<void>;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [session, setSession] = useState<Session | null>(null);
  const [profile, setProfile] = useState<Profile | null>(null);
  const [loading, setLoading] = useState(true);

  const fetchProfile = async (userId: string) => {
    // Always fetch the latest auth user to read metadata (including role)
    const { data: userData } = await supabase.auth.getUser();
    const authUser = userData.user;
    const metaRole = authUser?.user_metadata?.role as 'employer' | 'job_seeker' | undefined;

    const { data, error } = await supabase
      .from('profiles')
      .select('*')
      .eq('user_id', userId)
      .single();

    if (error) {
      if (error.code === 'PGRST116') {
        // Profile doesn't exist, create it
        if (authUser) {
          const roleToUse: 'employer' | 'job_seeker' =
            metaRole === 'employer' || metaRole === 'job_seeker' ? metaRole : 'job_seeker';

          const { data: newProfile, error: insertError } = await supabase
            .from('profiles')
            .insert({
              user_id: userId,
              full_name: authUser.user_metadata?.full_name || '',
              email: authUser.email || '',
              role: roleToUse,
            })
            .select()
            .single();

          if (!insertError && newProfile) {
            setProfile(newProfile);
            return;
          }
        }
      } else {
        console.error('Failed to fetch profile:', error);
      }

      setProfile(null);
      return;
    }

    // If a profile exists but has a different role than the auth metadata,
    // prefer the metadata and keep them in sync.
    if (authUser && metaRole && (metaRole === 'employer' || metaRole === 'job_seeker') && data.role !== metaRole) {
      const { data: updatedProfile, error: updateError } = await supabase
        .from('profiles')
        .update({ role: metaRole })
        .eq('user_id', userId)
        .select()
        .single();

      if (!updateError && updatedProfile) {
        setProfile(updatedProfile);
        return;
      }
    }

    setProfile(data);
  };

  const loadProfile = async (userId: string) => {
    setLoading(true);
    try {
      await fetchProfile(userId);
    } catch (err) {
      console.error('Error loading profile:', err);
      setProfile(null);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const { data: { subscription } } = supabase.auth.onAuthStateChange(
      async (_event, session) => {
        setSession(session);
        setUser(session?.user ?? null);
        if (session?.user) {
          await loadProfile(session.user.id);
        } else {
          setProfile(null);
          setLoading(false);
        }
      }
    );

    supabase.auth.getSession()
      .then(async ({ data: { session } }) => {
        setSession(session);
        setUser(session?.user ?? null);
        if (session?.user) {
          await loadProfile(session.user.id);
        } else {
          setLoading(false);
        }
      })
      .catch((err) => {
        console.error('Failed to get session:', err);
        setLoading(false);
      });

    return () => subscription.unsubscribe();
  }, []);

  const signUp = async (email: string, password: string, fullName: string, role: 'employer' | 'job_seeker') => {
    const { error } = await supabase.auth.signUp({
      email,
      password,
      options: {
        data: { full_name: fullName, role },
        emailRedirectTo: window.location.origin,
      },
    });
    if (error) throw error;

    // If the user is signed in immediately, update the profile role as well.
    const { data: { user: newUser } } = await supabase.auth.getUser();
    if (newUser) {
      await supabase.from('profiles').upsert({ user_id: newUser.id, role, full_name: fullName }, { onConflict: 'user_id' });
    }
  };

  const signIn = async (email: string, password: string) => {
    const { error } = await supabase.auth.signInWithPassword({ email, password });
    if (error) throw error;
  };

  const signOut = async () => {
    await supabase.auth.signOut();
    setProfile(null);
  };

  return (
    <AuthContext.Provider value={{ user, session, profile, loading, signUp, signIn, signOut }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth must be used within AuthProvider');
  return context;
};
