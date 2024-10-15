package pe.geff.gohab.onboarding.domain.repository

interface OnboardingRepository {
    fun hasSeenOnBoarding(): Boolean
    fun completeOnboarding()
}
