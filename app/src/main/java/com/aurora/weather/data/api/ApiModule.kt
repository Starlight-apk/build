package com.aurora.weather.data.api

import com.aurora.weather.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit 网络模块
 */
object ApiModule {
    
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    
    // 默认 API Key (OpenWeatherMap 免费额度)
    // 用户可以在 local.properties 中配置自己的 API Key
    private const val DEFAULT_API_KEY = "demo"
    
    val weatherApi: WeatherApiService by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        retrofit.create(WeatherApiService::class.java)
    }
    
    /**
     * 获取 API Key
     * 优先使用 local.properties 中配置的 API Key
     */
    fun getApiKey(): String {
        return BuildConfig.OPENWEATHER_API_KEY
    }
}
