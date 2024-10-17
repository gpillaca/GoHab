package pe.geff.gohab.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.geff.gohab.authentication.presentation.login.LoginScreen
import pe.geff.gohab.authentication.presentation.signup.SignUpScreen
import pe.geff.gohab.onboarding.presentation.OnboardingScreen

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
            LoginScreen(navigateTo = {
                if (NavigationRoute.Home == it) {
                    navController.popBackStack()
                }
                navController.navigate(it.route)
            })
        }
        composable(NavigationRoute.SignUp.route) {
            SignUpScreen(navigateTo = {
                navController.navigate(it.route)
            })
        }
        composable(NavigationRoute.Home.route) {
            Text(text = "Home Screen")
        }
    }
}
