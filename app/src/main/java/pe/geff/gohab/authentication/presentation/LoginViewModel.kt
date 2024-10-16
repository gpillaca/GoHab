package pe.geff.gohab.authentication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.geff.gohab.authentication.domain.usecase.EmailResult
import pe.geff.gohab.authentication.domain.usecase.LoginUseCase
import pe.geff.gohab.authentication.domain.usecase.PasswordResult
import pe.geff.gohab.authentication.domain.usecase.ValidateEmailUseCase
import pe.geff.gohab.authentication.domain.usecase.ValidatePasswordUseCase
import pe.geff.gohab.authentication.presentation.LoginEvent.EmailChange
import pe.geff.gohab.authentication.presentation.LoginEvent.Login
import pe.geff.gohab.authentication.presentation.LoginEvent.PasswordChange
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
): ViewModel() {

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
        }
    }

    private fun login() {
        loginState = loginState.copy(emailError = null, passwordError = null)
        val emailResult = validateEmailUseCase(loginState.email)
        val passwordResult = validatePasswordUseCase(loginState.password)

        if (emailResult is EmailResult.Invalid) {
            loginState = loginState.copy(emailError = emailResult.errorMessage)
        }

        if (passwordResult is PasswordResult.Invalid) {
            loginState = loginState.copy(passwordError = passwordResult.errorMessage)
        }

        if (!loginState.passwordError.isNullOrEmpty() && !loginState.emailError.isNullOrEmpty()) {
            loginState = loginState.copy(isLoading = true)
            viewModelScope.launch {
                loginUseCase(loginState.email, loginState.password).onSuccess {
                    loginState = loginState.copy(isLoggedIn = true)
                }.onFailure {
                    loginState = loginState.copy(passwordError = it.message)
                }
            }
            loginState = loginState.copy(isLoading = false)
        }
    }
}
