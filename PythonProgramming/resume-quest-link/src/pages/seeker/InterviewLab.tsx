import DashboardLayout from '@/components/DashboardLayout';
import { Card, CardContent } from '@/components/ui/card';
import { Mic, Radio, Calendar } from 'lucide-react';

export default function InterviewLab() {
  return (
    <DashboardLayout>
      <h2 className="text-2xl font-bold text-foreground mb-2">Interview Lab</h2>
      <p className="text-muted-foreground text-sm mb-6">Prepare for AI-powered voice interviews</p>

      <div className="grid md:grid-cols-2 gap-6">
        <Card className="gradient-card border-border shadow-card">
          <CardContent className="p-8 text-center">
            <div className="w-16 h-16 rounded-full gradient-primary flex items-center justify-center mx-auto mb-4 shadow-glow">
              <Mic className="w-8 h-8 text-primary-foreground" />
            </div>
            <h3 className="text-lg font-semibold text-foreground mb-2">AI Voice Interview</h3>
            <p className="text-muted-foreground text-sm mb-4">
              Practice with our AI interviewer. Get real-time feedback on your responses.
            </p>
            <div className="inline-block px-4 py-2 rounded-lg bg-muted text-muted-foreground text-sm">
              Coming Soon
            </div>
          </CardContent>
        </Card>

        <Card className="gradient-card border-border shadow-card">
          <CardContent className="p-8 text-center">
            <div className="w-16 h-16 rounded-full bg-secondary flex items-center justify-center mx-auto mb-4">
              <Calendar className="w-8 h-8 text-secondary-foreground" />
            </div>
            <h3 className="text-lg font-semibold text-foreground mb-2">Scheduled Interviews</h3>
            <p className="text-muted-foreground text-sm mb-4">
              View your upcoming interview sessions and preparation materials.
            </p>
            <div className="inline-block px-4 py-2 rounded-lg bg-muted text-muted-foreground text-sm">
              No interviews scheduled
            </div>
          </CardContent>
        </Card>

        <Card className="gradient-card border-border shadow-card md:col-span-2">
          <CardContent className="p-6 flex items-center gap-4">
            <div className="w-10 h-10 rounded-full bg-accent flex items-center justify-center shrink-0">
              <Radio className="w-5 h-5 text-accent-foreground" />
            </div>
            <div>
              <h3 className="font-semibold text-foreground">Live Transcript Viewer</h3>
              <p className="text-sm text-muted-foreground">
                Real-time transcription of your interview sessions will appear here during active interviews.
              </p>
            </div>
          </CardContent>
        </Card>
      </div>
    </DashboardLayout>
  );
}
