package com.aurora.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application 入口类
 */
@HiltAndroidApp
class AuroraWeatherApp : Application() {
    
    override fun onCreate() {
        super.onCreate()
        // 初始化全局组件
    }
}
