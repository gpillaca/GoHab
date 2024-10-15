package pe.geff.gohab.onboarding.domain.usecase

import pe.geff.gohab.onboarding.domain.repository.OnboardingRepository

class HasSeenOnboardingUseCase constructor(
    private val onBoardingRepository: OnboardingRepository
) {

    operator fun invoke(): Boolean {
        return onBoardingRepository.hasSeenOnBoarding()
    }
}
