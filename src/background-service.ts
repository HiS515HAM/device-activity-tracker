/**
 * Background Service for Device Activity Tracker
 * Allows tracking to continue even when app is minimized
 * 
 * For Android/Termux usage
 */

export class BackgroundTrackingService {
    private isRunning = false;
    private trackerId: string | null = null;
    private trackerPid: number | null = null;

    /**
     * Start background tracking
     * @param targetNumber Target phone number to track
     * @param platform 'whatsapp' or 'signal'
     */
    public async startBackgroundTracking(
        targetNumber: string,
        platform: 'whatsapp' | 'signal' = 'whatsapp'
    ): Promise<void> {
        this.isRunning = true;
        this.trackerId = `${platform}-${targetNumber}-${Date.now()}`;
        
        console.log('📱 Background tracking started');
        console.log(`   Target: ${targetNumber}`);
        console.log(`   Platform: ${platform}`);
        console.log(`   Tracker ID: ${this.trackerId}`);
        console.log(`   PID: ${process.pid}`);
        
        this.trackerPid = process.pid;
        
        // Handle graceful shutdown
        process.on('SIGTERM', () => {
            this.stopBackgroundTracking();
            process.exit(0);
        });
        
        process.on('SIGINT', () => {
            this.stopBackgroundTracking();
            process.exit(0);
        });
        
        // Keep process alive
        console.log('💾 Service running. Press Ctrl+C to stop.');
    }

    /**
     * Stop background tracking
     */
    public stopBackgroundTracking(): void {
        this.isRunning = false;
        console.log('🛑 Background tracking stopped');
        console.log(`   Tracker ID: ${this.trackerId}`);
        console.log(`   Uptime: ${Math.floor(process.uptime())}s`);
    }

    /**
     * Get service status
     */
    public getStatus() {
        return {
            isRunning: this.isRunning,
            trackerId: this.trackerId,
            pid: this.trackerPid,
            uptime: process.uptime(),
            memory: process.memoryUsage()
        };
    }

    /**
     * Get memory usage in MB
     */
    public getMemoryUsage(): number {
        return Math.round(process.memoryUsage().heapUsed / 1024 / 1024);
    }

    /**
     * Log status periodically
     */
    public startStatusLogging(interval: number = 60000): void {
        setInterval(() => {
            const status = this.getStatus();
            console.log(`\n📊 Status at ${new Date().toLocaleTimeString()}`);
            console.log(`   Memory: ${this.getMemoryUsage()}MB`);
            console.log(`   Uptime: ${Math.floor(status.uptime)}s`);
        }, interval);
    }
}

export default BackgroundTrackingService;
