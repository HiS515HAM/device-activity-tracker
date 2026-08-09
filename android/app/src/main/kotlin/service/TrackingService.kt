package com.deviceactivitytracker.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.deviceactivitytracker.R
import kotlinx.coroutines.*

class TrackingService : Service() {
    private val binder = LocalBinder()
    private var trackingJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default + Job())

    inner class LocalBinder : Binder() {
        fun getService(): TrackingService = this@TrackingService
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        trackingJob?.cancel()
        scope.cancel()
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    fun startTracking(phoneNumber: String) {
        trackingJob = scope.launch {
            // Tracking logic here
        }
    }

    fun stopTracking() {
        trackingJob?.cancel()
        trackingJob = null
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Device Activity Tracker")
            .setContentText("Tracking device activity...")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setOngoing(true)
            .build()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Device Tracking",
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "device_tracking_channel"
        private const val NOTIFICATION_ID = 1
    }
}
