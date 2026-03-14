package com.aurora.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

/**
 * 小时预报组件
 */
@Composable
fun HourlyForecast(
    modifier: Modifier = Modifier
) {
    // 模拟数据
    val hourlyData = listOf(
        HourlyForecastItem("14:00", 26, "晴"),
        HourlyForecastItem("15:00", 27, "晴"),
        HourlyForecastItem("16:00", 26, "多云"),
        HourlyForecastItem("17:00", 25, "多云"),
        HourlyForecastItem("18:00", 24, "阴"),
        HourlyForecastItem("19:00", 23, "阴"),
        HourlyForecastItem("20:00", 22, "晴"),
        HourlyForecastItem("21:00", 21, "晴")
    )
    
    Column(
        modifier = modifier
    ) {
        Text(
            text = "小时预报",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(hourlyData.size) { index ->
                HourlyForecastItemCard(
                    item = hourlyData[index]
                )
            }
        }
    }
}

data class HourlyForecastItem(
    val time: String,
    val temperature: Int,
    val condition: String
)

@Composable
fun HourlyForecastItemCard(
    item: HourlyForecastItem
) {
    Card(
        modifier = Modifier
            .width(80.dp)
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.time,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            
            Text(
                text = getWeatherIcon(item.condition),
                fontSize = 24.sp
            )
            
            Text(
                text = "${item.temperature}°",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

fun getWeatherIcon(condition: String): String {
    return when (condition) {
        "晴" -> "☀️"
        "多云" -> "⛅"
        "阴" -> "☁️"
        "雨" -> "🌧️"
        "雪" -> "❄️"
        else -> "☀️"
    }
}
