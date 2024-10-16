package pe.geff.gohab.authentication.domain

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun signUp(username: String, password: String): Result<Unit>
    fun userUid(): String?
}
