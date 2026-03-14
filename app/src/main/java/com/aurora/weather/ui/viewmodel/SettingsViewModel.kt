package com.aurora.weather.ui.viewmodel

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * 设置 ViewModel
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {
    
    // 设置键
    companion object {
        val REFRESH_INTERVAL_KEY = intPreferencesKey("refresh_interval")
        val TEMPERATURE_UNIT_KEY = stringPreferencesKey("temperature_unit")
        val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        val NOTIFICATIONS_ENABLED_KEY = booleanPreferencesKey("notifications_enabled")
    }
    
    // 刷新间隔（分钟）
    val refreshInterval: StateFlow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[REFRESH_INTERVAL_KEY] ?: 30
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 30
        )
    
    // 温度单位
    val temperatureUnit: StateFlow<String> = context.dataStore.data
        .map { preferences ->
            preferences[TEMPERATURE_UNIT_KEY] ?: "celsius"
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "celsius"
        )
    
    // 主题模式
    val themeMode: StateFlow<String> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_MODE_KEY] ?: "system"
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "system"
        )
    
    // 通知开关
    val notificationsEnabled: StateFlow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[NOTIFICATIONS_ENABLED_KEY] ?: true
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )
    
    /**
     * 更新刷新间隔
     */
    fun updateRefreshInterval(minutes: Int) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[REFRESH_INTERVAL_KEY] = minutes
            }
        }
    }
    
    /**
     * 更新温度单位
     */
    fun updateTemperatureUnit(unit: String) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[TEMPERATURE_UNIT_KEY] = unit
            }
        }
    }
    
    /**
     * 更新主题模式
     */
    fun updateThemeMode(mode: String) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[THEME_MODE_KEY] = mode
            }
        }
    }
    
    /**
     * 更新通知开关
     */
    fun updateNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[NOTIFICATIONS_ENABLED_KEY] = enabled
            }
        }
    }
}
