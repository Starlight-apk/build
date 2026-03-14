package com.aurora.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aurora.weather.ui.theme.AuroraBlue
import com.aurora.weather.ui.theme.AuroraGreen
import com.aurora.weather.ui.theme.AuroraPurple

/**
 * 天气详情卡片组件
 */
@Composable
fun WeatherDetailsCard(
    humidity: Int,
    windSpeed: Double,
    pressure: Int,
    visibility: Int,
    feelsLike: Double,
    uvIndex: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "天气详情",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 网格布局显示详情
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DetailItem(
                    icon = "💧",
                    label = "湿度",
                    value = "$humidity%",
                    color = AuroraBlue
                )
                
                DetailItem(
                    icon = "💨",
                    label = "风速",
                    value = "${windSpeed}m/s",
                    color = AuroraGreen
                )
                
                DetailItem(
                    icon = "🌡️",
                    label = "体感",
                    value = "${feelsLike.toInt()}°",
                    color = AuroraPurple
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DetailItem(
                    icon = "📊",
                    label = "气压",
                    value = "$pressure hPa",
                    color = AuroraBlue
                )
                
                DetailItem(
                    icon = "👁️",
                    label = "能见度",
                    value = "$visibility km",
                    color = AuroraGreen
                )
                
                DetailItem(
                    icon = "☀️",
                    label = "紫外线",
                    value = uvIndex.toString(),
                    color = AuroraPurple
                )
            }
        }
    }
}

@Composable
fun DetailItem(
    icon: String,
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = icon, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = color
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
        )
    }
}
