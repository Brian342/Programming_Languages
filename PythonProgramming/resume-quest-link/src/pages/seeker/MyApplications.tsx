import { useQuery } from '@tanstack/react-query';
import { supabase } from '@/integrations/supabase/client';
import { useAuth } from '@/contexts/AuthContext';
import DashboardLayout from '@/components/DashboardLayout';
import { Card, CardContent } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { FileText } from 'lucide-react';

export default function MyApplications() {
  const { user } = useAuth();

  const { data: applications = [], isLoading } = useQuery({
    queryKey: ['my-applications', user?.id],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('applications')
        .select('*, jobs(*)')
        .eq('applicant_id', user!.id)
        .order('created_at', { ascending: false });
      if (error) throw error;
      return data;
    },
    enabled: !!user,
  });

  const statusColor = (s: string) => {
    switch (s) {
      case 'accepted': return 'bg-primary/20 text-primary';
      case 'rejected': return 'bg-destructive/20 text-destructive';
      default: return 'bg-warning/20 text-warning';
    }
  };

  return (
    <DashboardLayout>
      <h2 className="text-2xl font-bold text-foreground mb-6">My Applications</h2>
      {isLoading ? (
        <p className="text-muted-foreground">Loading...</p>
      ) : applications.length === 0 ? (
        <div className="text-center py-16 text-muted-foreground">
          <FileText className="w-12 h-12 mx-auto mb-3 opacity-40" />
          <p>No applications yet. Start applying!</p>
        </div>
      ) : (
        <div className="grid gap-4">
          {applications.map((app: any) => (
            <Card key={app.id} className="gradient-card border-border shadow-card">
              <CardContent className="p-5 flex items-center justify-between">
                <div>
                  <h3 className="font-semibold text-foreground">{app.jobs?.title || 'Unknown'}</h3>
                  <p className="text-sm text-primary">{app.jobs?.company}</p>
                  <p className="text-xs text-muted-foreground mt-1">
                    Applied {new Date(app.created_at).toLocaleDateString()}
                  </p>
                </div>
                <Badge className={statusColor(app.status)}>{app.status}</Badge>
              </CardContent>
            </Card>
          ))}
        </div>
      )}
    </DashboardLayout>
  );
}
