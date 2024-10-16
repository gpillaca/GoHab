package pe.geff.gohab.navigation

sealed class NavigationRoute(val route: String) {
    data object Onboarding: NavigationRoute("onboarding")
    data object Login: NavigationRoute("login")
    data object SignUp: NavigationRoute("signup")
    data object Home: NavigationRoute("home")
}
