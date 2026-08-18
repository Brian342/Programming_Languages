import { useState } from 'react';
import { useAuth } from '@/contexts/AuthContext';
import { supabase } from '@/integrations/supabase/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { toast } from 'sonner';
import { Upload } from 'lucide-react';
import type { Tables } from '@/integrations/supabase/types';
import { useQueryClient } from '@tanstack/react-query';

interface ApplyFormProps {
  job: Tables<'jobs'>;
  onSuccess: () => void;
}

export default function ApplyForm({ job, onSuccess }: ApplyFormProps) {
  const { user } = useAuth();
  const queryClient = useQueryClient();
  const [contactEmail, setContactEmail] = useState(user?.email || '');
  const [contactPhone, setContactPhone] = useState('');
  const [coverLetter, setCoverLetter] = useState('');
  const [resumeFile, setResumeFile] = useState<File | null>(null);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!user) return;
    setLoading(true);

    try {
      let resumeUrl: string | null = null;

      if (resumeFile) {
        const filePath = `${user.id}/${Date.now()}_${resumeFile.name}`;
        const { error: uploadError } = await supabase.storage
          .from('resumes')
          .upload(filePath, resumeFile);
        if (uploadError) throw uploadError;
        const { data: urlData } = supabase.storage.from('resumes').getPublicUrl(filePath);
        resumeUrl = urlData.publicUrl;
      }

      const { error } = await supabase.from('applications').insert({
        job_id: job.id,
        applicant_id: user.id,
        contact_email: contactEmail,
        contact_phone: contactPhone || null,
        cover_letter: coverLetter || null,
        resume_url: resumeUrl,
      });

      if (error) throw error;

      toast.success('Application submitted!');
      queryClient.invalidateQueries({ queryKey: ['applications'] });
      onSuccess();
    } catch (err: any) {
      toast.error(err.message || 'Failed to apply');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div>
        <Label className="text-muted-foreground">Email</Label>
        <Input
          type="email"
          value={contactEmail}
          onChange={(e) => setContactEmail(e.target.value)}
          required
          className="mt-1 bg-muted border-border text-foreground"
        />
      </div>
      <div>
        <Label className="text-muted-foreground">Phone (optional)</Label>
        <Input
          type="tel"
          value={contactPhone}
          onChange={(e) => setContactPhone(e.target.value)}
          className="mt-1 bg-muted border-border text-foreground"
        />
      </div>
      <div>
        <Label className="text-muted-foreground">Cover Letter (optional)</Label>
        <Textarea
          value={coverLetter}
          onChange={(e) => setCoverLetter(e.target.value)}
          rows={3}
          className="mt-1 bg-muted border-border text-foreground resize-none"
        />
      </div>
      <div>
        <Label className="text-muted-foreground">Resume (PDF)</Label>
        <div className="mt-1">
          <label className="flex items-center gap-2 p-3 rounded-lg border border-dashed border-border bg-muted cursor-pointer hover:border-primary/50 transition-colors">
            <Upload className="w-4 h-4 text-muted-foreground" />
            <span className="text-sm text-muted-foreground">
              {resumeFile ? resumeFile.name : 'Click to upload PDF'}
            </span>
            <input
              type="file"
              accept=".pdf"
              className="hidden"
              onChange={(e) => setResumeFile(e.target.files?.[0] || null)}
            />
          </label>
        </div>
      </div>
      <Button
        type="submit"
        disabled={loading}
        className="w-full gradient-primary text-primary-foreground shadow-glow"
      >
        {loading ? 'Submitting...' : 'Submit Application'}
      </Button>
    </form>
  );
}
