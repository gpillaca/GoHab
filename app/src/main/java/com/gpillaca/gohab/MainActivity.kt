package com.gpillaca.gohab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gpillaca.gohab.navigation.NavigationHost
import com.gpillaca.gohab.navigation.NavigationRoute
import com.gpillaca.gohab.ui.theme.GoHabTheme

class MainActivity : ComponentActivity() {
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
                        startDestination = NavigationRoute.Onboarding
                    )
                }
            }
        }
    }
}
