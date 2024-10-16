package pe.geff.gohab.authentication.presentation

data class LoginState(
    val userUid: String? = null,
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false
)
