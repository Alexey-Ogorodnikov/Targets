package com.alexey.targets2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexey.targets2.presentation.screen.TargetListScreen

sealed class Screen(val route: String) {
    object TargetList : Screen("target_list")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.TargetList.route
    ) {
        composable(Screen.TargetList.route) {
            TargetListScreen()
        }
    }
} 