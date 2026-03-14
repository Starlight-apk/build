package com.aurora.weather.data.model

import com.google.gson.annotations.SerializedName

/**
 * OpenWeatherMap API 响应数据模型
 */
data class WeatherResponse(
    @SerializedName("main") val main: MainWeather,
    @SerializedName("weather") val weather: List<WeatherCondition>,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("name") val cityName: String,
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("sys") val sys: SysInfo,
    @SerializedName("coord") val coordinate: Coordinate
)

data class MainWeather(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int
)

data class WeatherCondition(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val degree: Int
)

data class SysInfo(
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)

data class Coordinate(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)
