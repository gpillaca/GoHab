package pe.geff.gohab.onboarding.domain.usecase

import pe.geff.gohab.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject

class CompleteOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    operator fun invoke() {
        onboardingRepository.completeOnboarding()
    }
}
