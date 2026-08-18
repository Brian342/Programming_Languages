import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { Toaster } from "@/components/ui/toaster";
import { TooltipProvider } from "@/components/ui/tooltip";
import { AuthProvider, useAuth } from "@/contexts/AuthContext";
import AuthPage from "./pages/AuthPage";
import JobBoard from "./pages/seeker/JobBoard";
import MyApplications from "./pages/seeker/MyApplications";
import InterviewLab from "./pages/seeker/InterviewLab";
import PostJob from "./pages/employer/PostJob";
import ApplicantTracker from "./pages/employer/ApplicantTracker";
import NotFound from "./pages/NotFound";

const queryClient = new QueryClient();

function ProtectedRoute({ children, requiredRole }: { children: React.ReactNode; requiredRole?: string }) {
  const { user, profile, loading } = useAuth();
  if (loading) return <div className="min-h-screen bg-background flex items-center justify-center text-muted-foreground">Loading...</div>;
  if (!user) return <Navigate to="/auth" replace />;
  if (!profile) {
    return (
      <div className="min-h-screen bg-background flex items-center justify-center text-muted-foreground px-4">
        <div className="max-w-md text-center">
          <p className="mb-4">We couldn&apos;t load your profile information.</p>
          <p className="text-sm text-muted-foreground mb-6">Please refresh the page or try signing out and signing back in.</p>
          <button
            onClick={() => window.location.reload()}
            className="px-4 py-2 rounded-lg bg-primary text-primary-foreground"
          >
            Refresh
          </button>
        </div>
      </div>
    );
  }
  if (requiredRole && profile.role !== requiredRole) {
    return <Navigate to={profile.role === 'employer' ? '/employer/post' : '/seeker/jobs'} replace />;
  }
  return <>{children}</>;
}

function AuthRoute() {
  const { user, profile, loading } = useAuth();
  if (loading) return <div className="min-h-screen bg-background flex items-center justify-center text-muted-foreground">Loading...</div>;
  if (user && profile) {
    return <Navigate to={profile.role === 'employer' ? '/employer/post' : '/seeker/jobs'} replace />;
  }
  return <AuthPage />;
}

function RootRedirect() {
  const { user, profile, loading } = useAuth();
  if (loading) return <div className="min-h-screen bg-background flex items-center justify-center text-muted-foreground">Loading...</div>;
  if (!user) return <Navigate to="/auth" replace />;
  return <Navigate to={profile?.role === 'employer' ? '/employer/post' : '/seeker/jobs'} replace />;
}

const App = () => (
  <QueryClientProvider client={queryClient}>
    <TooltipProvider>
      <Toaster />
      <Sonner />
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path="/" element={<RootRedirect />} />
            <Route path="/auth" element={<AuthRoute />} />
            <Route path="/seeker/jobs" element={<ProtectedRoute requiredRole="job_seeker"><JobBoard /></ProtectedRoute>} />
            <Route path="/seeker/applications" element={<ProtectedRoute requiredRole="job_seeker"><MyApplications /></ProtectedRoute>} />
            <Route path="/seeker/interviews" element={<ProtectedRoute requiredRole="job_seeker"><InterviewLab /></ProtectedRoute>} />
            <Route path="/employer/post" element={<ProtectedRoute requiredRole="employer"><PostJob /></ProtectedRoute>} />
            <Route path="/employer/applicants" element={<ProtectedRoute requiredRole="employer"><ApplicantTracker /></ProtectedRoute>} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </TooltipProvider>
  </QueryClientProvider>
);

export default App;
