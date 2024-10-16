package pe.geff.gohab.authentication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pe.geff.gohab.authentication.presentation.LoginEvent.EmailChange
import pe.geff.gohab.authentication.presentation.LoginEvent.Login
import pe.geff.gohab.authentication.presentation.LoginEvent.PasswordChange
import pe.geff.gohab.authentication.presentation.LoginEvent.SignUp
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when(event) {
            is EmailChange -> {
                loginState = loginState.copy(email = event.email)
            }
            Login -> login()
            is PasswordChange -> {
                loginState = loginState.copy(password = event.password)
            }
            SignUp -> {
                loginState = loginState.copy(signUp = true)
            }
        }
    }

    private fun login() {

    }
}
