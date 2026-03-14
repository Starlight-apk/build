package com.aurora.weather.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room 数据库
 */
@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AuroraWeatherDatabase : RoomDatabase() {
    
    abstract fun cityDao(): CityDao
    
    companion object {
        @Volatile
        private var INSTANCE: AuroraWeatherDatabase? = null
        
        fun getDatabase(context: Context): AuroraWeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AuroraWeatherDatabase::class.java,
                    "aurora_weather_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
