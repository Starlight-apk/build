package com.aurora.weather.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.*
import com.aurora.weather.data.worker.WeatherUpdateWorker
import java.util.concurrent.TimeUnit

/**
 * 开机启动接收器
 * 用于在设备重启后重新安排天气更新任务
 */
class BootReceiver : BroadcastReceiver() {
    
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            scheduleWeatherUpdate(context)
        }
    }
    
    /**
     * 安排定时天气更新
     */
    private fun scheduleWeatherUpdate(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<WeatherUpdateWorker>(
            30, TimeUnit.MINUTES
        )
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()
        
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "weather_update",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }
}
