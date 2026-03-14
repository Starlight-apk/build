package com.aurora.weather.data.model

import com.google.gson.annotations.SerializedName

/**
 * 天气预报响应数据模型
 */
data class ForecastResponse(
    @SerializedName("list") val forecastList: List<ForecastItem>,
    @SerializedName("city") val city: CityInfo
)

data class ForecastItem(
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("main") val main: MainWeather,
    @SerializedName("weather") val weather: List<WeatherCondition>,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("pop") val precipitationProbability: Double
)

data class CityInfo(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("coord") val coordinate: Coordinate
)
