import { useState } from 'react';
import { useAuth } from '@/contexts/AuthContext';
import { supabase } from '@/integrations/supabase/client';
import { useQuery, useQueryClient } from '@tanstack/react-query';
import DashboardLayout from '@/components/DashboardLayout';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { toast } from 'sonner';
import { Plus, Briefcase, MapPin, Trash2 } from 'lucide-react';
import type { Tables } from '@/integrations/supabase/types';

type Job = Tables<'jobs'>;

export default function PostJob() {
  const { user } = useAuth();
  const queryClient = useQueryClient();
  const [title, setTitle] = useState('');
  const [company, setCompany] = useState('');
  const [location, setLocation] = useState('Remote');
  const [jobType, setJobType] = useState('Full-time');
  const [salaryRange, setSalaryRange] = useState('');
  const [description, setDescription] = useState('');
  const [requirements, setRequirements] = useState('');
  const [tagsInput, setTagsInput] = useState('');
  const [loading, setLoading] = useState(false);

  const { data: myJobs = [] } = useQuery({
    queryKey: ['my-jobs', user?.id],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('jobs')
        .select('*')
        .eq('employer_id', user!.id)
        .order('created_at', { ascending: false });
      if (error) throw error;
      return data as Job[];
    },
    enabled: !!user,
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!user) return;
    setLoading(true);

    try {
      const tags = tagsInput
        .split(',')
        .map((t) => t.trim())
        .filter(Boolean);

      const { error } = await supabase.from('jobs').insert({
        employer_id: user.id,
        title,
        company,
        location,
        job_type: jobType,
        salary_range: salaryRange || null,
        description,
        requirements: requirements || null,
        tags,
      });

      if (error) throw error;

      toast.success('Job posted successfully!');
      setTitle('');
      setCompany('');
      setDescription('');
      setRequirements('');
      setTagsInput('');
      setSalaryRange('');
      queryClient.invalidateQueries({ queryKey: ['my-jobs'] });
      queryClient.invalidateQueries({ queryKey: ['jobs'] });
    } catch (err: any) {
      toast.error(err.message || 'Failed to post job');
    } finally {
      setLoading(false);
    }
  };

  const deleteJob = async (jobId: string) => {
    const { error } = await supabase.from('jobs').delete().eq('id', jobId);
    if (error) {
      toast.error('Failed to delete');
    } else {
      toast.success('Job deleted');
      queryClient.invalidateQueries({ queryKey: ['my-jobs'] });
    }
  };

  return (
    <DashboardLayout>
      <div className="grid lg:grid-cols-2 gap-8">
        {/* Post Form */}
        <div>
          <h2 className="text-2xl font-bold text-foreground mb-6 flex items-center gap-2">
            <Plus className="w-6 h-6 text-primary" /> Post a New Job
          </h2>
          <Card className="gradient-card border-border shadow-card">
            <CardContent className="p-6">
              <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                  <Label className="text-muted-foreground">Job Title</Label>
                  <Input value={title} onChange={(e) => setTitle(e.target.value)} required className="mt-1 bg-muted border-border text-foreground" placeholder="Senior React Developer" />
                </div>
                <div>
                  <Label className="text-muted-foreground">Company</Label>
                  <Input value={company} onChange={(e) => setCompany(e.target.value)} required className="mt-1 bg-muted border-border text-foreground" placeholder="Acme Corp" />
                </div>
                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <Label className="text-muted-foreground">Location</Label>
                    <Input value={location} onChange={(e) => setLocation(e.target.value)} className="mt-1 bg-muted border-border text-foreground" />
                  </div>
                  <div>
                    <Label className="text-muted-foreground">Job Type</Label>
                    <Input value={jobType} onChange={(e) => setJobType(e.target.value)} className="mt-1 bg-muted border-border text-foreground" />
                  </div>
                </div>
                <div>
                  <Label className="text-muted-foreground">Salary Range</Label>
                  <Input value={salaryRange} onChange={(e) => setSalaryRange(e.target.value)} className="mt-1 bg-muted border-border text-foreground" placeholder="$80k - $120k" />
                </div>
                <div>
                  <Label className="text-muted-foreground">Description</Label>
                  <Textarea value={description} onChange={(e) => setDescription(e.target.value)} required rows={4} className="mt-1 bg-muted border-border text-foreground resize-none" />
                </div>
                <div>
                  <Label className="text-muted-foreground">Requirements</Label>
                  <Textarea value={requirements} onChange={(e) => setRequirements(e.target.value)} rows={3} className="mt-1 bg-muted border-border text-foreground resize-none" />
                </div>
                <div>
                  <Label className="text-muted-foreground">Tags (comma-separated)</Label>
                  <Input value={tagsInput} onChange={(e) => setTagsInput(e.target.value)} className="mt-1 bg-muted border-border text-foreground" placeholder="React, TypeScript, Remote" />
                </div>
                <Button type="submit" disabled={loading} className="w-full gradient-primary text-primary-foreground shadow-glow">
                  {loading ? 'Posting...' : 'Publish Job'}
                </Button>
              </form>
            </CardContent>
          </Card>
        </div>

        {/* My Jobs */}
        <div>
          <h2 className="text-2xl font-bold text-foreground mb-6 flex items-center gap-2">
            <Briefcase className="w-6 h-6 text-primary" /> Your Jobs ({myJobs.length})
          </h2>
          <div className="space-y-3">
            {myJobs.map((job) => (
              <Card key={job.id} className="gradient-card border-border shadow-card">
                <CardContent className="p-4 flex items-start justify-between">
                  <div>
                    <h3 className="font-semibold text-foreground">{job.title}</h3>
                    <p className="text-sm text-primary">{job.company}</p>
                    <span className="text-xs text-muted-foreground flex items-center gap-1 mt-1">
                      <MapPin className="w-3 h-3" /> {job.location}
                    </span>
                  </div>
                  <Button variant="ghost" size="sm" onClick={() => deleteJob(job.id)} className="text-muted-foreground hover:text-destructive">
                    <Trash2 className="w-4 h-4" />
                  </Button>
                </CardContent>
              </Card>
            ))}
            {myJobs.length === 0 && (
              <p className="text-center text-muted-foreground py-8">No jobs posted yet.</p>
            )}
          </div>
        </div>
      </div>
    </DashboardLayout>
  );
}
