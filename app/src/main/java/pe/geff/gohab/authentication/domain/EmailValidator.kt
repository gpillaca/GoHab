package pe.geff.gohab.authentication.domain

interface EmailValidator {
    fun isEmailValid(email: String): Boolean
}
