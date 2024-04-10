package com.gpillaca.gohab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gpillaca.gohab.onboarding.OnboardingScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: NavigationRoute
) {

    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(startDestination.route) {
            OnboardingScreen(onComplete = {
                println("Onboarding: Navigate")
            })
        }
    }

}
