package com.aurora.weather.ui.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 * 主屏幕导航
 */
sealed class Screen(val route: String, val title: String) {
    object Weather : Screen("weather", "天气")
    object Forecast : Screen("forecast", "预报")
    object Map : Screen("map", "地图")
    object Cities : Screen("cities", "城市")
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedTab by rememberSaveable { mutableStateOf(0) }
    
    val navItems = listOf(
        Screen.Weather to Icons.Filled.Today to Icons.Outlined.Today,
        Screen.Forecast to Icons.Filled.LocationOn to Icons.Outlined.LocationOn,
        Screen.Map to Icons.Filled.Map to Icons.Outlined.Map,
        Screen.Cities to Icons.Filled.Settings to Icons.Outlined.Settings
    )
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                navItems.forEachIndexed { index, (screen, filledIcon, outlinedIcon) ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (selectedTab == index) filledIcon else outlinedIcon,
                                contentDescription = screen.title
                            )
                        },
                        label = { Text(screen.title) },
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Weather.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            composable(Screen.Weather.route) {
                WeatherPage()
            }
            composable(Screen.Forecast.route) {
                ForecastPage()
            }
            composable(Screen.Map.route) {
                MapPage()
            }
            composable(Screen.Cities.route) {
                CitiesPage()
            }
        }
    }
}
