package com.aurora.weather.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora.weather.ui.theme.AuroraBlue
import com.aurora.weather.ui.theme.AuroraGreen
import com.aurora.weather.ui.theme.AuroraPink
import com.aurora.weather.ui.theme.AuroraPurple

/**
 * 当前温度显示组件
 */
@Composable
fun CurrentTemperatureDisplay(
    temperature: Double,
    condition: String,
    city: String,
    modifier: Modifier = Modifier
) {
    //  Aurora 渐变背景动画
    val infiniteTransition = rememberInfiniteTransition(label = "aurora")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset"
    )
    
    val auroraBrush = Brush.linearGradient(
        colors = listOf(
            AuroraGreen.copy(alpha = 0.3f),
            AuroraBlue.copy(alpha = 0.3f),
            AuroraPurple.copy(alpha = 0.3f),
            AuroraPink.copy(alpha = 0.3f)
        ),
        start = androidx.compose.ui.geometry.Offset(offset, 0f),
        end = androidx.compose.ui.geometry.Offset(offset + 500f, 500f)
    )
    
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(auroraBrush)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 城市名称
        Text(
            text = city,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // 温度显示（大字体）
        Text(
            text = "${temperature.toInt()}°",
            fontSize = 72.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // 天气状况
        Text(
            text = condition,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
        )
    }
}
