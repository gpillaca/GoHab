package pe.geff.gohab.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import pe.geff.gohab.R
import pe.geff.gohab.onboarding.OnboardingPagerInformation
import pe.geff.gohab.onboarding.component.OnboardingPager
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onComplete: () -> Unit
) {

    LaunchedEffect(key1 = viewModel.hasSeenOnboarding) {
        if (viewModel.hasSeenOnboarding) {
            onComplete()
        }
    }

    val data = mutableListOf(
        OnboardingPagerInformation(
            title = "Welcome to GoGab \n Good Habits",
            message = "Habits are fundamental part of our life. Make the most of your life!",
            image = R.drawable.onboarding1
        ),
        OnboardingPagerInformation(
            title = "Create new habit easily",
            message = "You cant create new habits",
            image = R.drawable.onboarding2
        ),
        OnboardingPagerInformation(
            title = "Keep track your progress",
            message = "Keep track of your personal growth and see how tiny steps lead you towards",
            image = R.drawable.onboarding3
        )
    )
    OnboardingPager(data, onComplete = {
        viewModel.completeOnboarding()
    })
}
