package com.aurora.weather.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.weather.ui.components.*
import com.aurora.weather.ui.theme.AuroraWeatherTheme

/**
 * 天气页面（当前天气）
 */
@Composable
fun WeatherPage() {
    // TODO: 接入 ViewModel 获取真实数据
    // 暂时使用模拟数据展示 UI
    
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 搜索栏
        SearchBar(
            onSearch = { /* TODO: 搜索城市 */ },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 当前温度显示
        CurrentTemperatureDisplay(
            temperature = 25.5,
            condition = "晴",
            city = "北京市",
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 天气详情卡片
        WeatherDetailsCard(
            humidity = 65,
            windSpeed = 3.5,
            pressure = 1013,
            visibility = 10,
            feelsLike = 27.0,
            uvIndex = 5,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 日出日落卡片
        SunCard(
            sunrise = "06:30",
            sunset = "18:45",
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 小时预报
        HourlyForecast(
            modifier = Modifier.fillMaxWidth()
        )
    }
}
