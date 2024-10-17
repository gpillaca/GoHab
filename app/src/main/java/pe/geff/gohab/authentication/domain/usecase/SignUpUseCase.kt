package pe.geff.gohab.authentication.domain.usecase

import pe.geff.gohab.authentication.domain.AuthenticationRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return authenticationRepository.signUp(email, password)
    }
}
