package com.aurora.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.weather.data.model.Weather
import com.aurora.weather.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 天气 ViewModel
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState
    
    private val _forecastUiState = MutableStateFlow<ForecastUiState>(ForecastUiState.Loading)
    val forecastUiState: StateFlow<ForecastUiState> = _forecastUiState
    
    /**
     * 加载天气数据（基于坐标）
     */
    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            weatherRepository.getCurrentWeather(lat, lon)
                .onSuccess { weather ->
                    _uiState.value = WeatherUiState.Success(weather)
                }
                .onFailure { error ->
                    _uiState.value = WeatherUiState.Error(error.message ?: "未知错误")
                }
        }
    }
    
    /**
     * 加载天气数据（基于城市名）
     */
    fun loadWeather(cityName: String) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            weatherRepository.getCurrentWeather(cityName)
                .onSuccess { weather ->
                    _uiState.value = WeatherUiState.Success(weather)
                }
                .onFailure { error ->
                    _uiState.value = WeatherUiState.Error(error.message ?: "未知错误")
                }
        }
    }
    
    /**
     * 加载天气预报
     */
    fun loadForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            _forecastUiState.value = ForecastUiState.Loading
            weatherRepository.getForecast(lat, lon)
                .onSuccess { forecast ->
                    _forecastUiState.value = ForecastUiState.Success(forecast)
                }
                .onFailure { error ->
                    _forecastUiState.value = ForecastUiState.Error(error.message ?: "未知错误")
                }
        }
    }
    
    /**
     * 加载天气预报（基于城市名）
     */
    fun loadForecast(cityName: String) {
        viewModelScope.launch {
            _forecastUiState.value = ForecastUiState.Loading
            weatherRepository.getForecast(cityName)
                .onSuccess { forecast ->
                    _forecastUiState.value = ForecastUiState.Success(forecast)
                }
                .onFailure { error ->
                    _forecastUiState.value = ForecastUiState.Error(error.message ?: "未知错误")
                }
        }
    }
}

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val weather: Weather) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

sealed class ForecastUiState {
    object Loading : ForecastUiState()
    data class Success(val forecast: com.aurora.weather.data.model.Forecast) : ForecastUiState()
    data class Error(val message: String) : ForecastUiState()
}
