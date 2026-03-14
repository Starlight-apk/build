package com.aurora.weather.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 城市实体类
 */
@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey val id: Long = System.currentTimeMillis(),
    val name: String,
    val country: String = "",
    val latitude: Double,
    val longitude: Double,
    val isAutoLocation: Boolean = false,
    val order: Int = 0,
    val lastUpdated: Long = 0L
)
