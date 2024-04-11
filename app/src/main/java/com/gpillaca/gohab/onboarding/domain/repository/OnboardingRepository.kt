package com.gpillaca.gohab.onboarding.domain.repository

interface OnboardingRepository {
    fun hasSeenOnBoarding(): Boolean
    fun completeOnboarding()
}
