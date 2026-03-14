package com.aurora.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.weather.data.database.CityDao
import com.aurora.weather.data.database.CityEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 城市管理 ViewModel
 */
@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val cityDao: CityDao
) : ViewModel() {
    
    val cities: StateFlow<List<CityEntity>> = cityDao.getAllCities()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    /**
     * 添加城市
     */
    fun addCity(name: String, country: String, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val existingCount = cityDao.getCityCount()
            val city = CityEntity(
                name = name,
                country = country,
                latitude = latitude,
                longitude = longitude,
                order = existingCount
            )
            cityDao.insertCity(city)
        }
    }
    
    /**
     * 删除城市
     */
    fun deleteCity(city: CityEntity) {
        viewModelScope.launch {
            cityDao.deleteCity(city)
        }
    }
    
    /**
     * 设置自动定位城市
     */
    fun setAutoLocationCity(lat: Double, lon: Double) {
        viewModelScope.launch {
            val existing = cityDao.getAutoLocationCity()
            if (existing != null) {
                cityDao.deleteCity(existing)
            }
            val city = CityEntity(
                name = "当前位置",
                latitude = lat,
                longitude = lon,
                isAutoLocation = true,
                order = 0
            )
            cityDao.insertCity(city)
        }
    }
}
