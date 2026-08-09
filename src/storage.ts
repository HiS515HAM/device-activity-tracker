/**
 * Local Storage Management
 * Save tracking data locally for offline use and historical data
 */

import * as fs from 'fs';
import * as path from 'path';

interface TrackedData {
    timestamp: number;
    targetNumber: string;
    state: string;
    rtt: number;
    platform: 'whatsapp' | 'signal';
}

export class LocalStorage {
    private dataDir = './local_data';
    private historyDir = './local_data/history';

    constructor() {
        if (!fs.existsSync(this.dataDir)) {
            fs.mkdirSync(this.dataDir, { recursive: true });
        }
        if (!fs.existsSync(this.historyDir)) {
            fs.mkdirSync(this.historyDir, { recursive: true });
        }
    }

    /**
     * Save tracking data
     */
    public saveTracking(targetNumber: string, data: any): void {
        try {
            const file = path.join(this.dataDir, `${targetNumber}.json`);
            fs.writeFileSync(file, JSON.stringify(data, null, 2));
            console.log(`✅ Saved tracking for ${targetNumber}`);
        } catch (err) {
            console.error(`❌ Error saving tracking: ${err}`);
        }
    }

    /**
     * Load tracking data
     */
    public loadTracking(targetNumber: string): any {
        try {
            const file = path.join(this.dataDir, `${targetNumber}.json`);
            if (fs.existsSync(file)) {
                return JSON.parse(fs.readFileSync(file, 'utf-8'));
            }
            return null;
        } catch (err) {
            console.error(`❌ Error loading tracking: ${err}`);
            return null;
        }
    }

    /**
     * Save historical data point
     */
    public saveHistoricalPoint(data: TrackedData): void {
        try {
            const date = new Date();
            const dateStr = date.toISOString().split('T')[0];
            const file = path.join(this.historyDir, `${data.targetNumber}-${dateStr}.json`);
            
            let history: TrackedData[] = [];
            if (fs.existsSync(file)) {
                history = JSON.parse(fs.readFileSync(file, 'utf-8'));
            }
            
            history.push(data);
            fs.writeFileSync(file, JSON.stringify(history, null, 2));
        } catch (err) {
            console.error(`❌ Error saving history: ${err}`);
        }
    }

    /**
     * Get historical data for a target
     */
    public getHistory(targetNumber: string, days: number = 7): TrackedData[] {
        try {
            let allData: TrackedData[] = [];
            
            for (let i = 0; i < days; i++) {
                const date = new Date();
                date.setDate(date.getDate() - i);
                const dateStr = date.toISOString().split('T')[0];
                const file = path.join(this.historyDir, `${targetNumber}-${dateStr}.json`);
                
                if (fs.existsSync(file)) {
                    const data = JSON.parse(fs.readFileSync(file, 'utf-8'));
                    allData = allData.concat(data);
                }
            }
            
            return allData.sort((a, b) => a.timestamp - b.timestamp);
        } catch (err) {
            console.error(`❌ Error loading history: ${err}`);
            return [];
        }
    }

    /**
     * Get all tracked numbers
     */
    public getAllTracking(): any[] {
        try {
            const files = fs.readdirSync(this.dataDir);
            return files
                .filter(f => f.endsWith('.json') && f !== 'config.json')
                .map(f => {
                    const file = path.join(this.dataDir, f);
                    return JSON.parse(fs.readFileSync(file, 'utf-8'));
                });
        } catch (err) {
            console.error(`❌ Error loading all tracking: ${err}`);
            return [];
        }
    }

    /**
     * Delete tracking data
     */
    public deleteTracking(targetNumber: string): void {
        try {
            const file = path.join(this.dataDir, `${targetNumber}.json`);
            if (fs.existsSync(file)) {
                fs.unlinkSync(file);
                console.log(`✅ Deleted tracking for ${targetNumber}`);
            }
        } catch (err) {
            console.error(`❌ Error deleting tracking: ${err}`);
        }
    }

    /**
     * Clear all data
     */
    public clearAllData(): void {
        try {
            const files = fs.readdirSync(this.dataDir);
            files.forEach(f => {
                if (f.endsWith('.json')) {
                    fs.unlinkSync(path.join(this.dataDir, f));
                }
            });
            console.log('✅ Cleared all data');
        } catch (err) {
            console.error(`❌ Error clearing data: ${err}`);
        }
    }

    /**
     * Export data as CSV
     */
    public exportAsCSV(targetNumber: string, outputFile: string): void {
        try {
            const history = this.getHistory(targetNumber);
            const csv = [
                'Timestamp,Date,Time,State,RTT(ms),Platform',
                ...history.map(h => 
                    `${h.timestamp},${new Date(h.timestamp).toLocaleDateString()},${new Date(h.timestamp).toLocaleTimeString()},${h.state},${h.rtt},${h.platform}`
                )
            ].join('\n');
            
            fs.writeFileSync(outputFile, csv);
            console.log(`✅ Exported to ${outputFile}`);
        } catch (err) {
            console.error(`❌ Error exporting CSV: ${err}`);
        }
    }

    /**
     * Get storage size in MB
     */
    public getStorageSize(): number {
        try {
            let totalSize = 0;
            const files = fs.readdirSync(this.dataDir);
            files.forEach(f => {
                const filePath = path.join(this.dataDir, f);
                const stats = fs.statSync(filePath);
                totalSize += stats.size;
            });
            return Math.round(totalSize / 1024 / 1024);
        } catch (err) {
            return 0;
        }
    }
}

export default LocalStorage;
