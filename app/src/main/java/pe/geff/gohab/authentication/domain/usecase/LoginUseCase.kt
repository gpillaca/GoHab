package pe.geff.gohab.authentication.domain.usecase

import pe.geff.gohab.authentication.domain.AuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return authenticationRepository.login(username, password)
    }
}
