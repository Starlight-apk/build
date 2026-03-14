package com.aurora.weather.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aurora.weather.ui.components.CityItem
import com.aurora.weather.data.database.CityEntity

/**
 * 城市管理页面
 */
@Composable
fun CitiesPage() {
    // 模拟数据
    var cities by remember {
        mutableStateOf(
            listOf(
                CityEntity(1, "北京市", "CN", 39.9042, 116.4074, true, 0),
                CityEntity(2, "上海市", "CN", 31.2304, 121.4737, false, 1),
                CityEntity(3, "广州市", "CN", 23.1291, 113.2644, false, 2),
                CityEntity(4, "深圳市", "CN", 22.5431, 114.0579, false, 3)
            )
        )
    }
    
    var showAddDialog by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "我的城市",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            if (cities.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "暂无城市，点击右上角添加",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cities, key = { it.id }) { city ->
                        CityItem(
                            city = city,
                            onDelete = {
                                cities = cities.filter { it.id != city.id }
                            }
                        )
                    }
                }
            }
        }
        
        // 添加按钮
        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "添加城市"
            )
        }
    }
    
    // 添加城市对话框
    if (showAddDialog) {
        AddCityDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { cityName ->
                // TODO: 根据城市名获取坐标
                val newCity = CityEntity(
                    name = cityName,
                    latitude = 39.9042,
                    longitude = 116.4074,
                    order = cities.size
                )
                cities = cities + newCity
                showAddDialog = false
            }
        )
    }
}

@Composable
fun AddCityDialog(
    onDismiss: () -> Unit,
    onAdd: (String) -> Unit
) {
    var cityName by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加城市") },
        text = {
            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("城市名称") },
                placeholder = { Text("例如：北京") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onAdd(cityName) },
                enabled = cityName.isNotBlank()
            ) {
                Text("添加")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    )
}
