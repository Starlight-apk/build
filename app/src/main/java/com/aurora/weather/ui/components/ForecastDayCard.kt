package com.aurora.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.weather.ui.theme.AuroraBlue
import com.aurora.weather.ui.theme.AuroraGreen

/**
 * 预报日卡片组件
 */
@Composable
fun ForecastDayCard(
    item: ForecastDayItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 日期
            Text(
                text = item.day,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
            
            // 天气图标
            Text(
                text = getWeatherIcon(item.condition),
                fontSize = 32.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            // 温度范围
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${item.tempMax}°",
                    style = MaterialTheme.typography.titleLarge,
                    color = AuroraGreen,
                    modifier = Modifier.padding(end = 8.dp)
                )
                
                Text(
                    text = "${item.tempMin}°",
                    style = MaterialTheme.typography.titleMedium,
                    color = AuroraBlue.copy(alpha = 0.7f)
                )
            }
        }
    }
}
