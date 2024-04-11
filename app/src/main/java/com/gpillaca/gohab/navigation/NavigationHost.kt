package com.gpillaca.gohab.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gpillaca.gohab.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: NavigationRoute
) {

    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(NavigationRoute.Onboarding.route) {
            OnboardingScreen(onComplete = {
                navController.popBackStack()
                navController.navigate(NavigationRoute.Login.route)
            })
        }
        composable(NavigationRoute.Login.route) {
            Text(text = "login screen")
        }
    }
}
