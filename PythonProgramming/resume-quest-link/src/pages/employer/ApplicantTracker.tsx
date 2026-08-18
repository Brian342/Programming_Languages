import { useQuery, useQueryClient } from '@tanstack/react-query';
import { supabase } from '@/integrations/supabase/client';
import { useAuth } from '@/contexts/AuthContext';
import DashboardLayout from '@/components/DashboardLayout';
import { Card, CardContent } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Progress } from '@/components/ui/progress';
import { Dialog, DialogContent, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { toast } from 'sonner';
import { useState } from 'react';
import { FileText, CheckCircle, XCircle, ExternalLink, Users } from 'lucide-react';

export default function ApplicantTracker() {
  const { user } = useAuth();
  const queryClient = useQueryClient();
  const [selectedResume, setSelectedResume] = useState<string | null>(null);

  const { data: applications = [], isLoading } = useQuery({
    queryKey: ['employer-applications', user?.id],
    queryFn: async () => {
      // Get employer's jobs first
      const { data: jobs } = await supabase
        .from('jobs')
        .select('id')
        .eq('employer_id', user!.id);

      if (!jobs || jobs.length === 0) return [];

      const jobIds = jobs.map((j) => j.id);
      const { data, error } = await supabase
        .from('applications')
        .select('*, jobs(title, company)')
        .in('job_id', jobIds)
        .order('created_at', { ascending: false });

      // Fetch profiles for applicants
      if (data && data.length > 0) {
        const applicantIds = [...new Set(data.map((a: any) => a.applicant_id))];
        const { data: profiles } = await supabase
          .from('profiles')
          .select('user_id, full_name, email')
          .in('user_id', applicantIds);

        const profileMap = new Map((profiles || []).map((p: any) => [p.user_id, p]));
        return data.map((app: any) => ({
          ...app,
          profiles: profileMap.get(app.applicant_id) || null,
        }));
      }

      if (error) throw error;
      return data;
    },
    enabled: !!user,
  });

  const updateStatus = async (appId: string, status: string) => {
    const { error } = await supabase
      .from('applications')
      .update({ status })
      .eq('id', appId);
    if (error) {
      toast.error('Failed to update');
    } else {
      toast.success(status === 'accepted' ? 'Congratulations email queued!' : 'Rejection sent.');
      queryClient.invalidateQueries({ queryKey: ['employer-applications'] });
    }
  };

  const scoreColor = (score: number) => {
    if (score >= 70) return 'text-primary';
    if (score >= 40) return 'text-warning';
    return 'text-destructive';
  };

  const progressColor = (score: number) => {
    if (score >= 70) return '[&>div]:bg-primary';
    if (score >= 40) return '[&>div]:bg-yellow-500';
    return '[&>div]:bg-destructive';
  };

  return (
    <DashboardLayout>
      <h2 className="text-2xl font-bold text-foreground mb-2 flex items-center gap-2">
        <Users className="w-6 h-6 text-primary" /> Applicant Tracker
      </h2>
      <p className="text-muted-foreground text-sm mb-6">{applications.length} total applications</p>

      {isLoading ? (
        <p className="text-muted-foreground">Loading...</p>
      ) : applications.length === 0 ? (
        <div className="text-center py-16 text-muted-foreground">
          <Users className="w-12 h-12 mx-auto mb-3 opacity-40" />
          <p>No applications received yet.</p>
        </div>
      ) : (
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="border-b border-border">
                <th className="text-left py-3 px-4 text-sm font-medium text-muted-foreground">Candidate</th>
                <th className="text-left py-3 px-4 text-sm font-medium text-muted-foreground">Job Applied For</th>
                <th className="text-left py-3 px-4 text-sm font-medium text-muted-foreground">Match Score</th>
                <th className="text-left py-3 px-4 text-sm font-medium text-muted-foreground">Status</th>
                <th className="text-right py-3 px-4 text-sm font-medium text-muted-foreground">Actions</th>
              </tr>
            </thead>
            <tbody>
              {applications.map((app: any) => {
                const score = app.match_score ?? 0;
                const name = app.profiles?.full_name || app.contact_email;
                return (
                  <tr key={app.id} className="border-b border-border/50 hover:bg-muted/30 transition-colors">
                    <td className="py-4 px-4">
                      <div className="font-medium text-foreground">{name}</div>
                      <div className="text-xs text-muted-foreground">{app.contact_email}</div>
                    </td>
                    <td className="py-4 px-4">
                      <div className="text-sm text-foreground">{app.jobs?.title}</div>
                      <div className="text-xs text-muted-foreground">{app.jobs?.company}</div>
                    </td>
                    <td className="py-4 px-4">
                      <div className="flex items-center gap-3 min-w-[140px]">
                        <Progress value={score} className={`h-2 flex-1 bg-muted ${progressColor(score)}`} />
                        <span className={`text-sm font-semibold ${scoreColor(score)}`}>{score}%</span>
                      </div>
                    </td>
                    <td className="py-4 px-4">
                      <span className={`text-xs font-medium px-2 py-1 rounded-full ${
                        app.status === 'accepted'
                          ? 'bg-primary/20 text-primary'
                          : app.status === 'rejected'
                          ? 'bg-destructive/20 text-destructive'
                          : 'bg-secondary text-secondary-foreground'
                      }`}>
                        {app.status}
                      </span>
                    </td>
                    <td className="py-4 px-4">
                      <div className="flex items-center justify-end gap-2">
                        {app.resume_url && (
                          <Button
                            variant="ghost"
                            size="sm"
                            onClick={() => setSelectedResume(app.resume_url)}
                            className="text-muted-foreground hover:text-foreground"
                          >
                            <FileText className="w-4 h-4" />
                          </Button>
                        )}
                        {app.status === 'pending' && (
                          <>
                            <Button
                              size="sm"
                              onClick={() => updateStatus(app.id, 'accepted')}
                              className="bg-primary/20 text-primary hover:bg-primary/30 text-xs"
                            >
                              <CheckCircle className="w-3.5 h-3.5 mr-1" /> Accept
                            </Button>
                            <Button
                              size="sm"
                              variant="ghost"
                              onClick={() => updateStatus(app.id, 'rejected')}
                              className="text-destructive hover:bg-destructive/10 text-xs"
                            >
                              <XCircle className="w-3.5 h-3.5 mr-1" /> Reject
                            </Button>
                          </>
                        )}
                      </div>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      )}

      {/* Resume Viewer */}
      <Dialog open={!!selectedResume} onOpenChange={() => setSelectedResume(null)}>
        <DialogContent className="bg-card border-border text-foreground max-w-3xl h-[80vh]">
          <DialogHeader>
            <DialogTitle className="flex items-center justify-between">
              Resume
              {selectedResume && (
                <a href={selectedResume} target="_blank" rel="noopener noreferrer" className="text-primary text-sm flex items-center gap-1">
                  Open <ExternalLink className="w-3.5 h-3.5" />
                </a>
              )}
            </DialogTitle>
          </DialogHeader>
          {selectedResume && (
            <iframe src={selectedResume} className="w-full flex-1 rounded-lg bg-muted" title="Resume" />
          )}
        </DialogContent>
      </Dialog>
    </DashboardLayout>
  );
}
