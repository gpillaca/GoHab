package pe.geff.gohab.authentication.data

import android.util.Patterns
import pe.geff.gohab.authentication.domain.EmailValidator
import javax.inject.Inject

class DataEmailValidator @Inject constructor(): EmailValidator {
    override fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
