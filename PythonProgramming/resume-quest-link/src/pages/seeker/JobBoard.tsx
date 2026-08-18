import { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { supabase } from '@/integrations/supabase/client';
import DashboardLayout from '@/components/DashboardLayout';
import { Input } from '@/components/ui/input';
import { Badge } from '@/components/ui/badge';
import { Card, CardContent } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Search, MapPin, Clock, DollarSign, X } from 'lucide-react';
import { Dialog, DialogContent, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import ApplyForm from '@/components/ApplyForm';
import type { Tables } from '@/integrations/supabase/types';

type Job = Tables<'jobs'>;

export default function JobBoard() {
  const [search, setSearch] = useState('');
  const [selectedTag, setSelectedTag] = useState<string | null>(null);
  const [selectedJob, setSelectedJob] = useState<Job | null>(null);
  const [applyingJob, setApplyingJob] = useState<Job | null>(null);

  const { data: jobs = [], isLoading } = useQuery({
    queryKey: ['jobs'],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('jobs')
        .select('*')
        .eq('is_active', true)
        .order('created_at', { ascending: false });
      if (error) throw error;
      return data as Job[];
    },
  });

  const allTags = [...new Set(jobs.flatMap((j) => j.tags || []))];

  const filtered = jobs.filter((job) => {
    const matchSearch =
      !search ||
      job.title.toLowerCase().includes(search.toLowerCase()) ||
      job.company.toLowerCase().includes(search.toLowerCase());
    const matchTag = !selectedTag || (job.tags || []).includes(selectedTag);
    return matchSearch && matchTag;
  });

  return (
    <DashboardLayout>
      <div className="mb-6">
        <h2 className="text-2xl font-bold text-foreground mb-1">Find Your Next Role</h2>
        <p className="text-muted-foreground text-sm">{filtered.length} positions available</p>
      </div>

      {/* Search & Filters */}
      <div className="mb-6 space-y-3">
        <div className="relative">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-muted-foreground" />
          <Input
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            placeholder="Search by title or company..."
            className="pl-10 bg-muted border-border text-foreground placeholder:text-muted-foreground"
          />
        </div>
        {allTags.length > 0 && (
          <div className="flex flex-wrap gap-2">
            {allTags.map((tag) => (
              <Badge
                key={tag}
                variant={selectedTag === tag ? 'default' : 'outline'}
                className={`cursor-pointer transition-colors ${
                  selectedTag === tag
                    ? 'bg-primary text-primary-foreground'
                    : 'border-border text-muted-foreground hover:text-foreground'
                }`}
                onClick={() => setSelectedTag(selectedTag === tag ? null : tag)}
              >
                {tag}
              </Badge>
            ))}
          </div>
        )}
      </div>

      {/* Job Cards */}
      {isLoading ? (
        <div className="text-center py-12 text-muted-foreground">Loading jobs...</div>
      ) : filtered.length === 0 ? (
        <div className="text-center py-12 text-muted-foreground">No jobs found.</div>
      ) : (
        <div className="grid gap-4">
          {filtered.map((job) => (
            <Card
              key={job.id}
              className="gradient-card border-border shadow-card hover:border-primary/30 transition-all cursor-pointer"
              onClick={() => setSelectedJob(job)}
            >
              <CardContent className="p-5">
                <div className="flex flex-col sm:flex-row sm:items-start justify-between gap-3">
                  <div className="flex-1">
                    <h3 className="text-lg font-semibold text-foreground">{job.title}</h3>
                    <p className="text-primary font-medium text-sm">{job.company}</p>
                    <div className="flex flex-wrap items-center gap-3 mt-2 text-sm text-muted-foreground">
                      <span className="flex items-center gap-1">
                        <MapPin className="w-3.5 h-3.5" />
                        {job.location}
                      </span>
                      <span className="flex items-center gap-1">
                        <Clock className="w-3.5 h-3.5" />
                        {job.job_type}
                      </span>
                      {job.salary_range && (
                        <span className="flex items-center gap-1">
                          <DollarSign className="w-3.5 h-3.5" />
                          {job.salary_range}
                        </span>
                      )}
                    </div>
                    {(job.tags || []).length > 0 && (
                      <div className="flex flex-wrap gap-1.5 mt-3">
                        {(job.tags || []).map((tag) => (
                          <Badge key={tag} variant="outline" className="text-xs border-border text-muted-foreground">
                            {tag}
                          </Badge>
                        ))}
                      </div>
                    )}
                  </div>
                  <Button
                    className="gradient-primary text-primary-foreground shadow-glow shrink-0"
                    onClick={(e) => {
                      e.stopPropagation();
                      setApplyingJob(job);
                    }}
                  >
                    Apply Now
                  </Button>
                </div>
              </CardContent>
            </Card>
          ))}
        </div>
      )}

      {/* Job Detail Dialog */}
      <Dialog open={!!selectedJob} onOpenChange={() => setSelectedJob(null)}>
        <DialogContent className="bg-card border-border text-foreground max-w-2xl max-h-[80vh] overflow-y-auto">
          {selectedJob && (
            <>
              <DialogHeader>
                <DialogTitle className="text-xl">{selectedJob.title}</DialogTitle>
                <p className="text-primary font-medium">{selectedJob.company}</p>
              </DialogHeader>
              <div className="flex flex-wrap gap-3 text-sm text-muted-foreground my-2">
                <span className="flex items-center gap-1"><MapPin className="w-3.5 h-3.5" />{selectedJob.location}</span>
                <span className="flex items-center gap-1"><Clock className="w-3.5 h-3.5" />{selectedJob.job_type}</span>
                {selectedJob.salary_range && (
                  <span className="flex items-center gap-1"><DollarSign className="w-3.5 h-3.5" />{selectedJob.salary_range}</span>
                )}
              </div>
              <div className="space-y-4 mt-4">
                <div>
                  <h4 className="font-semibold text-foreground mb-1">Description</h4>
                  <p className="text-muted-foreground text-sm whitespace-pre-wrap">{selectedJob.description}</p>
                </div>
                {selectedJob.requirements && (
                  <div>
                    <h4 className="font-semibold text-foreground mb-1">Requirements</h4>
                    <p className="text-muted-foreground text-sm whitespace-pre-wrap">{selectedJob.requirements}</p>
                  </div>
                )}
              </div>
              <Button
                className="w-full mt-4 gradient-primary text-primary-foreground shadow-glow"
                onClick={() => {
                  setSelectedJob(null);
                  setApplyingJob(selectedJob);
                }}
              >
                Apply Now
              </Button>
            </>
          )}
        </DialogContent>
      </Dialog>

      {/* Apply Dialog */}
      <Dialog open={!!applyingJob} onOpenChange={() => setApplyingJob(null)}>
        <DialogContent className="bg-card border-border text-foreground max-w-lg">
          {applyingJob && (
            <>
              <DialogHeader>
                <DialogTitle>Apply to {applyingJob.title}</DialogTitle>
              </DialogHeader>
              <ApplyForm job={applyingJob} onSuccess={() => setApplyingJob(null)} />
            </>
          )}
        </DialogContent>
      </Dialog>
    </DashboardLayout>
  );
}
