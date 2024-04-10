package com.gpillaca.gohab.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: NavigationRoute
) {

    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(startDestination.route) {
            // Onboarding
            Text(text = "Test Onboarding")
        }
    }

}
