package pe.geff.gohab.authentication.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import pe.geff.gohab.authentication.domain.AuthenticationRepository
import javax.inject.Inject

class DataAuthenticationRepository @Inject constructor(): AuthenticationRepository {
    override suspend fun login(username: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.signInWithEmailAndPassword(username, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(username: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(username, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun userUid(): String? {
        return Firebase.auth.currentUser?.uid
    }
}
