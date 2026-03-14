package com.aurora.weather.util

import com.aurora.weather.data.model.Weather

/**
 * 天气工具类
 */
object WeatherUtils {
    
    /**
     * 根据天气条件 ID 获取天气图标
     */
    fun getWeatherIcon(conditionId: Int): String {
        return when {
            conditionId == 800 -> "☀️" // 晴
            conditionId in 801..804 -> "⛅" // 多云
            conditionId in 200..232 -> "⛈️" // 雷暴
            conditionId in 300..321 -> "🌧️" // 毛毛雨
            conditionId in 500..531 -> "🌧️" // 雨
            conditionId in 600..622 -> "❄️" // 雪
            conditionId in 701..781 -> "🌫️" // 雾
            else -> "☀️"
        }
    }
    
    /**
     * 根据天气条件 ID 获取天气描述
     */
    fun getWeatherDescription(conditionId: Int): String {
        return when {
            conditionId == 800 -> "晴"
            conditionId in 801..804 -> "多云"
            conditionId in 200..232 -> "雷暴"
            conditionId in 300..321 -> "毛毛雨"
            conditionId in 500..531 -> "雨"
            conditionId in 600..622 -> "雪"
            conditionId in 701..781 -> "雾"
            else -> "未知"
        }
    }
    
    /**
     * 判断是否是恶劣天气（需要预警）
     */
    fun isSevereWeather(weather: Weather): Boolean {
        return weather.conditionId in 200..232 || // 雷暴
               weather.conditionId in 700..781 || // 雾/霾
               weather.windSpeed > 15 || // 大风
               weather.visibility < 1000 // 低能见度
    }
    
    /**
     * 格式化温度
     */
    fun formatTemperature(temp: Double, unit: String = "celsius"): String {
        return when (unit) {
            "fahrenheit" -> "${((temp * 9/5) + 32).toInt()}°F"
            else -> "${temp.toInt()}°C"
        }
    }
    
    /**
     * 格式化风速
     */
    fun formatWindSpeed(speed: Double): String {
        return "%.1f m/s".format(speed)
    }
    
    /**
     * 格式化时间戳
     */
    fun formatTime(timestamp: Long): String {
        val sdf = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(timestamp * 1000))
    }
    
    /**
     * 格式化日期
     */
    fun formatDate(timestamp: Long): String {
        val sdf = java.text.SimpleDateFormat("EEEE", java.util.Locale.CHINA)
        return sdf.format(java.util.Date(timestamp * 1000))
    }
}
