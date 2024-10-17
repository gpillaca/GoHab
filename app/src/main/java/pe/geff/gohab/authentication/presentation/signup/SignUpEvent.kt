package pe.geff.gohab.authentication.presentation.signup

sealed interface SignUpEvent {
    data object SignUp: SignUpEvent
    data class EmailChange(val email: String): SignUpEvent
    data class PasswordChange(val password: String): SignUpEvent
}
