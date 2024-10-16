package pe.geff.gohab.authentication.domain.usecase

import pe.geff.gohab.authentication.domain.EmailValidator
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    private val emailValidator: EmailValidator
) {
    operator fun invoke(email: String): EmailResult {
        return if (emailValidator.isEmailValid(email)) {
            EmailResult.Valid
        } else {
            EmailResult.Invalid("Invalid email")
        }
    }
}

sealed class EmailResult {
    data object Valid : EmailResult()
    data class Invalid(val errorMessage: String) : EmailResult()
}
