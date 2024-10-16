package pe.geff.gohab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import pe.geff.gohab.navigation.NavigationHost
import pe.geff.gohab.navigation.NavigationRoute
import pe.geff.gohab.ui.theme.GoHabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GoHabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navController = navController,
                        startDestination = getStartDestination()
                    )
                }
            }
        }
    }

    private fun getStartDestination(): NavigationRoute {
        if (viewModel.isLoggedIn) {
            return NavigationRoute.Home
        }

        return if (viewModel.hasSeenOnboarding) {
            NavigationRoute.Login
        } else {
            NavigationRoute.Onboarding
        }
    }
}
