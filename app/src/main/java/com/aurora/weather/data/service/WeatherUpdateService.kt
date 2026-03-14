package com.aurora.weather.data.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.aurora.weather.R
import com.aurora.weather.ui.MainActivity

/**
 * 天气更新后台服务
 */
class WeatherUpdateService : Service() {
    
    companion object {
        const val NOTIFICATION_ID = 1001
        const val CHANNEL_ID = "weather_update_channel"
        const val ACTION_UPDATE_WEATHER = "com.aurora.weather.UPDATE_WEATHER"
    }
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_UPDATE_WEATHER -> {
                // TODO: 执行天气更新
                stopForeground(STOP_FOREGROUND_REMOVE)
            }
        }
        return START_NOT_STICKY
    }
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "天气更新",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "后台天气更新服务"
            }
            
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    fun createNotification(): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("天气更新")
            .setContentText("正在更新天气信息...")
            .setSmallIcon(R.drawable.ic_weather_clear)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }
}
