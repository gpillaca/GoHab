package pe.geff.gohab.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.geff.gohab.authentication.domain.usecase.EmailResult
import pe.geff.gohab.authentication.domain.usecase.PasswordResult
import pe.geff.gohab.authentication.domain.usecase.SignUpUseCase
import pe.geff.gohab.authentication.domain.usecase.ValidateEmailUseCase
import pe.geff.gohab.authentication.domain.usecase.ValidatePasswordUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    fun onEvent(event: SignUpEvent) {
        when (event) {
            SignUpEvent.SignUp -> {
                signUp()
            }

            is SignUpEvent.EmailChange -> {
                state = state.copy(email = event.email)
            }

            is SignUpEvent.PasswordChange -> {
                state = state.copy(password = event.password)
            }
        }
    }

    private fun signUp() {
        state = state.copy(emailError = null, passwordError = null)
        val emailResult = validateEmailUseCase(state.email)
        val passwordResult = validatePasswordUseCase(state.password)

        if (emailResult is EmailResult.Invalid) {
            state = state.copy(emailError = emailResult.errorMessage)
        }

        if (passwordResult is PasswordResult.Invalid) {
            state = state.copy(passwordError = passwordResult.errorMessage)
        }

        if (state.emailError.isNullOrEmpty() && state.passwordError.isNullOrEmpty()) {
            state = state.copy(isLoading = true)
            viewModelScope.launch {
                signUpUseCase(state.email, state.password).onSuccess {
                    state = state.copy(isLoggedIn = true)
                }.onFailure {
                    state = state.copy(passwordError = it.message)
                }
            }
            state = state.copy(isLoading = true)
        }

    }
}
