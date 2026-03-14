package com.aurora.weather.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.weather.ui.components.ForecastDayCard

/**
 * 天气预报页面
 */
@Composable
fun ForecastPage() {
    // 模拟数据
    val forecastData = listOf(
        ForecastDayItem("今天", 28, 18, "晴"),
        ForecastDayItem("明天", 26, 17, "多云"),
        ForecastDayItem("后天", 24, 16, "阴"),
        ForecastDayItem("周四", 22, 15, "雨"),
        ForecastDayItem("周五", 20, 14, "雨"),
        ForecastDayItem("周六", 23, 15, "多云"),
        ForecastDayItem("周日", 25, 16, "晴")
    )
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "7 天预报",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        items(forecastData.size) { index ->
            ForecastDayCard(
                item = forecastData[index]
            )
        }
    }
}

data class ForecastDayItem(
    val day: String,
    val tempMax: Int,
    val tempMin: Int,
    val condition: String
)
