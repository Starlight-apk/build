package com.aurora.weather.data.api

import com.aurora.weather.data.model.ForecastResponse
import com.aurora.weather.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * OpenWeatherMap API 接口
 */
interface WeatherApiService {
    
    /**
     * 获取当前天气
     * @param lat 纬度
     * @param lon 经度
     * @param appId API Key
     * @param units 单位 (metric=摄氏度)
     * @param lang 语言
     */
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "zh_cn"
    ): Response<WeatherResponse>
    
    /**
     * 获取城市天气
     * @param q 城市名称
     * @param appId API Key
     * @param units 单位
     * @param lang 语言
     */
    @GET("weather")
    suspend fun getCityWeather(
        @Query("q") q: String,
        @Query("appid") appId: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "zh_cn"
    ): Response<WeatherResponse>
    
    /**
     * 获取天气预报 (5 天/3 小时)
     * @param lat 纬度
     * @param lon 经度
     * @param appId API Key
     * @param units 单位
     * @param lang 语言
     */
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "zh_cn"
    ): Response<ForecastResponse>
    
    /**
     * 获取城市天气预报
     * @param q 城市名称
     * @param appId API Key
     * @param units 单位
     * @param lang 语言
     */
    @GET("forecast")
    suspend fun getCityForecast(
        @Query("q") q: String,
        @Query("appid") appId: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "zh_cn"
    ): Response<ForecastResponse>
}
