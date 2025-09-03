package com.janitri.pregnancyvitals.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.janitri.pregnancyvitals.R
import com.janitri.pregnancyvitals.ui.MainActivity
import kotlinx.coroutines.*

class TimerService : Service() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private var timerJob: Job? = null
    private var elapsedTime = 0L

    companion object {
        const val TIMER_UPDATED = "timer_updated"
        const val TIME_ELAPSED = "time_elapsed"
        const val NOTIFICATION_ID = 123
        const val CHANNEL_ID = "TimerChannel"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundService()
        startTimer()
        return START_STICKY
    }

    private fun startTimer() {
        if (timerJob?.isActive == true) return // Timer already running

        timerJob = scope.launch {
            while (isActive) {
                delay(1000)
                elapsedTime++
                val intent = Intent(TIMER_UPDATED).apply {
                    putExtra(TIME_ELAPSED, elapsedTime)
                }
                sendBroadcast(intent)
            }
        }
    }

    private fun startForegroundService() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Timer Service",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Pregnancy Tracker")
            .setContentText("Timer is running...")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Add a default icon
            .setContentIntent(pendingIntent)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // We are not using a bound service
    }
}