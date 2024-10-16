package pe.geff.gohab.authentication.domain.usecase

import pe.geff.gohab.authentication.data.DataAuthenticationRepository
import javax.inject.Inject

class GetUserUidUseCase @Inject constructor(
    private val authenticationRepository: DataAuthenticationRepository
) {
    operator fun invoke(): String? {
        return authenticationRepository.userUid()
    }
}
