package pe.geff.gohab.authentication.domain.usecase

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {

    operator fun invoke(password: String): PasswordResult {
        return if (password.length >= 6) {
            PasswordResult.Valid
        } else {
            PasswordResult.Invalid("Password must be at least 6 characters long")
        }
    }

}

sealed class PasswordResult {
    data object Valid : PasswordResult()
    data class Invalid(val errorMessage: String) : PasswordResult()
}
