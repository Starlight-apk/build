package com.aurora.weather.data.model

/**
 * 应用内部天气数据模型
 */
data class Weather(
    val city: String,
    val temperature: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val condition: String,
    val conditionDescription: String,
    val conditionIcon: String,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val windDegree: Int,
    val visibility: Int,
    val sunrise: Long,
    val sunset: Long,
    val timestamp: Long,
    val latitude: Double,
    val longitude: Double,
    val conditionId: Int = 0
) {
    // 根据天气条件 ID 判断天气类型
    fun isClear(): Boolean = conditionId in 800..800
    fun isCloudy(): Boolean = conditionId in 801..804
    fun isRain(): Boolean = conditionId in 200..232 || conditionId in 300..321 || conditionId in 500..531
    fun isSnow(): Boolean = conditionId in 600..622
    fun isThunderstorm(): Boolean = conditionId in 200..232
    fun isFog(): Boolean = conditionId in 701..781
}
