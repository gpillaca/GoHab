package com.gpillaca.gohab.onboarding.domain.usecase

import com.gpillaca.gohab.onboarding.domain.repository.OnboardingRepository

class HasSeenOnboardingUseCase constructor(
    private val onBoardingRepository: OnboardingRepository
) {

    operator fun invoke(): Boolean {
        return onBoardingRepository.hasSeenOnBoarding()
    }
}
