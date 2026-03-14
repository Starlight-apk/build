package com.aurora.weather.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * 城市数据访问对象
 */
@Dao
interface CityDao {
    
    @Query("SELECT * FROM cities ORDER BY `order` ASC")
    fun getAllCities(): Flow<List<CityEntity>>
    
    @Query("SELECT * FROM cities WHERE isAutoLocation = 1 LIMIT 1")
    suspend fun getAutoLocationCity(): CityEntity?
    
    @Query("SELECT * FROM cities WHERE id = :id")
    suspend fun getCityById(id: Long): CityEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity): Long
    
    @Update
    suspend fun updateCity(city: CityEntity)
    
    @Delete
    suspend fun deleteCity(city: CityEntity)
    
    @Query("DELETE FROM cities WHERE id = :id")
    suspend fun deleteCityById(id: Long)
    
    @Query("UPDATE cities SET `order` = :order WHERE id = :id")
    suspend fun updateCityOrder(id: Long, order: Int)
    
    @Query("SELECT COUNT(*) FROM cities")
    suspend fun getCityCount(): Int
}
