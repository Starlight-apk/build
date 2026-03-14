package com.aurora.weather.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aurora.weather.data.repository.WeatherRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * 天气更新后台工作器
 */
@HiltWorker
class WeatherUpdateWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val weatherRepository: WeatherRepository
) : CoroutineWorker(context, workerParams) {
    
    override suspend fun doWork(): Result {
        return try {
            // TODO: 获取用户位置并更新天气
            // 暂时返回成功
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
