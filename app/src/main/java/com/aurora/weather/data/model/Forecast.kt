package com.aurora.weather.data.model

/**
 * 应用内部天气预报数据模型
 */
data class Forecast(
    val city: String,
    val country: String,
    val items: List<ForecastItem>
)

data class ForecastItem(
    val timestamp: Long,
    val temperature: Double,
    val feelsLike: Double,
    val condition: String,
    val conditionDescription: String,
    val conditionIcon: String,
    val humidity: Int,
    val windSpeed: Double,
    val precipitationProbability: Double,
    val conditionId: Int = 0
) {
    fun isClear(): Boolean = conditionId in 800..800
    fun isCloudy(): Boolean = conditionId in 801..804
    fun isRain(): Boolean = conditionId in 200..232 || conditionId in 300..321 || conditionId in 500..531
    fun isSnow(): Boolean = conditionId in 600..622
    fun isThunderstorm(): Boolean = conditionId in 200..232
    fun isFog(): Boolean = conditionId in 701..781
}
