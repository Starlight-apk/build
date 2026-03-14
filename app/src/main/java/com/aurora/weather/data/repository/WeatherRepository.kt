package com.aurora.weather.data.repository

import com.aurora.weather.data.api.ApiModule
import com.aurora.weather.data.model.Forecast
import com.aurora.weather.data.model.ForecastItem
import com.aurora.weather.data.model.Weather
import java.io.IOException

/**
 * 天气数据仓库
 */
class WeatherRepository {
    
    private val weatherApi = ApiModule.weatherApi
    
    /**
     * 获取当前天气 (基于坐标)
     */
    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<Weather> {
        return try {
            val response = weatherApi.getCurrentWeather(
                lat = lat,
                lon = lon,
                appId = ApiModule.getApiKey()
            )
            
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                val weather = Weather(
                    city = data.cityName,
                    temperature = data.main.temperature,
                    feelsLike = data.main.feelsLike,
                    tempMin = data.main.tempMin,
                    tempMax = data.main.tempMax,
                    condition = data.weather.firstOrNull()?.main ?: "",
                    conditionDescription = data.weather.firstOrNull()?.description ?: "",
                    conditionIcon = data.weather.firstOrNull()?.icon ?: "",
                    humidity = data.main.humidity,
                    pressure = data.main.pressure,
                    windSpeed = data.wind.speed,
                    windDegree = data.wind.degree,
                    visibility = data.visibility,
                    sunrise = data.sys.sunrise,
                    sunset = data.sys.sunset,
                    timestamp = data.timestamp,
                    latitude = data.coordinate.latitude,
                    longitude = data.coordinate.longitude,
                    conditionId = data.weather.firstOrNull()?.id ?: 0
                )
                Result.success(weather)
            } else {
                Result.failure(IOException("Weather API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * 获取当前天气 (基于城市名称)
     */
    suspend fun getCurrentWeather(cityName: String): Result<Weather> {
        return try {
            val response = weatherApi.getCityWeather(
                q = cityName,
                appId = ApiModule.getApiKey()
            )
            
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                val weather = Weather(
                    city = data.cityName,
                    temperature = data.main.temperature,
                    feelsLike = data.main.feelsLike,
                    tempMin = data.main.tempMin,
                    tempMax = data.main.tempMax,
                    condition = data.weather.firstOrNull()?.main ?: "",
                    conditionDescription = data.weather.firstOrNull()?.description ?: "",
                    conditionIcon = data.weather.firstOrNull()?.icon ?: "",
                    humidity = data.main.humidity,
                    pressure = data.main.pressure,
                    windSpeed = data.wind.speed,
                    windDegree = data.wind.degree,
                    visibility = data.visibility,
                    sunrise = data.sys.sunrise,
                    sunset = data.sys.sunset,
                    timestamp = data.timestamp,
                    latitude = data.coordinate.latitude,
                    longitude = data.coordinate.longitude,
                    conditionId = data.weather.firstOrNull()?.id ?: 0
                )
                Result.success(weather)
            } else {
                Result.failure(IOException("Weather API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * 获取天气预报 (基于坐标)
     */
    suspend fun getForecast(lat: Double, lon: Double): Result<Forecast> {
        return try {
            val response = weatherApi.getForecast(
                lat = lat,
                lon = lon,
                appId = ApiModule.getApiKey()
            )
            
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                val forecastItems = data.forecastList
                    .groupBy { it.timestamp / (24 * 60 * 60) } // 按天分组
                    .map { (_, items) ->
                        // 取每天中午 12 点的数据作为代表
                        val noonItem = items.find { 
                            java.text.SimpleDateFormat("HH", java.util.Locale.getDefault())
                                .format(java.util.Date(it.timestamp * 1000)) == "12" 
                        } ?: items.first()
                        
                        ForecastItem(
                            timestamp = noonItem.timestamp,
                            temperature = noonItem.main.temperature,
                            feelsLike = noonItem.main.feelsLike,
                            condition = noonItem.weather.firstOrNull()?.main ?: "",
                            conditionDescription = noonItem.weather.firstOrNull()?.description ?: "",
                            conditionIcon = noonItem.weather.firstOrNull()?.icon ?: "",
                            humidity = noonItem.main.humidity,
                            windSpeed = noonItem.wind.speed,
                            precipitationProbability = noonItem.precipitationProbability,
                            conditionId = noonItem.weather.firstOrNull()?.id ?: 0
                        )
                    }
                    .take(7) // 只取 7 天
                
                Result.success(
                    Forecast(
                        city = data.city.name,
                        country = data.city.country,
                        items = forecastItems
                    )
                )
            } else {
                Result.failure(IOException("Forecast API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * 获取天气预报 (基于城市名称)
     */
    suspend fun getForecast(cityName: String): Result<Forecast> {
        return try {
            val response = weatherApi.getCityForecast(
                q = cityName,
                appId = ApiModule.getApiKey()
            )
            
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                val forecastItems = data.forecastList
                    .groupBy { it.timestamp / (24 * 60 * 60) }
                    .map { (_, items) ->
                        val noonItem = items.find { 
                            java.text.SimpleDateFormat("HH", java.util.Locale.getDefault())
                                .format(java.util.Date(it.timestamp * 1000)) == "12" 
                        } ?: items.first()
                        
                        ForecastItem(
                            timestamp = noonItem.timestamp,
                            temperature = noonItem.main.temperature,
                            feelsLike = noonItem.main.feelsLike,
                            condition = noonItem.weather.firstOrNull()?.main ?: "",
                            conditionDescription = noonItem.weather.firstOrNull()?.description ?: "",
                            conditionIcon = noonItem.weather.firstOrNull()?.icon ?: "",
                            humidity = noonItem.main.humidity,
                            windSpeed = noonItem.wind.speed,
                            precipitationProbability = noonItem.precipitationProbability,
                            conditionId = noonItem.weather.firstOrNull()?.id ?: 0
                        )
                    }
                    .take(7)
                
                Result.success(
                    Forecast(
                        city = data.city.name,
                        country = data.city.country,
                        items = forecastItems
                    )
                )
            } else {
                Result.failure(IOException("Forecast API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
