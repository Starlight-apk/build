package com.aurora.weather.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.aurora.weather.ui.viewmodel.SettingsViewModel

/**
 * 设置页面
 */
@Composable
fun SettingsPage(
    settingsViewModel: SettingsViewModel
) {
    val refreshInterval by settingsViewModel.refreshInterval.collectAsState()
    val temperatureUnit by settingsViewModel.temperatureUnit.collectAsState()
    val themeMode by settingsViewModel.themeMode.collectAsState()
    val notificationsEnabled by settingsViewModel.notificationsEnabled.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "设置",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 刷新间隔设置
        SettingsSection(title = "刷新间隔") {
            RefreshIntervalSetting(
                currentInterval = refreshInterval,
                onIntervalChanged = { settingsViewModel.updateRefreshInterval(it) }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 温度单位设置
        SettingsSection(title = "温度单位") {
            TemperatureUnitSetting(
                currentUnit = temperatureUnit,
                onUnitChanged = { settingsViewModel.updateTemperatureUnit(it) }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 主题设置
        SettingsSection(title = "主题") {
            ThemeModeSetting(
                currentMode = themeMode,
                onModeChanged = { settingsViewModel.updateThemeMode(it) }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 通知设置
        SettingsSection(title = "通知") {
            NotificationsSetting(
                enabled = notificationsEnabled,
                onEnabledChanged = { settingsViewModel.updateNotificationsEnabled(it) }
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 关于
        SettingsSection(title = "关于") {
            AboutSection()
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        content()
    }
}

@Composable
fun RefreshIntervalSetting(
    currentInterval: Int,
    onIntervalChanged: (Int) -> Unit
) {
    val options = listOf(15, 30, 60)
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        options.forEach { interval ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (interval == currentInterval),
                        onClick = { onIntervalChanged(interval) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (interval == currentInterval),
                    onClick = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$interval 分钟",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun TemperatureUnitSetting(
    currentUnit: String,
    onUnitChanged: (String) -> Unit
) {
    val options = listOf("celsius" to "摄氏度 (°C)", "fahrenheit" to "华氏度 (°F)")
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .selectableGroup()
    ) {
        options.forEach { (value, label) ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (value == currentUnit),
                        onClick = { onUnitChanged(value) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (value == currentUnit),
                    onClick = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun ThemeModeSetting(
    currentMode: String,
    onModeChanged: (String) -> Unit
) {
    val options = listOf(
        "system" to "跟随系统",
        "light" to "浅色",
        "dark" to "深色"
    )
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .selectableGroup()
    ) {
        options.forEach { (value, label) ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (value == currentMode),
                        onClick = { onModeChanged(value) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (value == currentMode),
                    onClick = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun NotificationsSetting(
    enabled: Boolean,
    onEnabledChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "天气预警通知",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "接收恶劣天气预警推送",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = enabled,
            onCheckedChange = onEnabledChanged
        )
    }
}

@Composable
fun AboutSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "极光天气",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "版本 1.0.0",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "一款采用 Material You 设计风格的现代化天气应用",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
