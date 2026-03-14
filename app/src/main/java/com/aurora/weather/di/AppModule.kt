package com.aurora.weather.di

import android.content.Context
import com.aurora.weather.data.database.AuroraWeatherDatabase
import com.aurora.weather.data.database.CityDao
import com.aurora.weather.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt 依赖注入模块
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AuroraWeatherDatabase {
        return AuroraWeatherDatabase.getDatabase(context)
    }
    
    @Provides
    @Singleton
    fun provideCityDao(database: AuroraWeatherDatabase): CityDao {
        return database.cityDao()
    }
    
    @Provides
    @Singleton
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository()
    }
}
