package com.gpillaca.gohab.onboarding

import androidx.compose.runtime.Composable
import com.gpillaca.gohab.R
import com.gpillaca.gohab.onboarding.component.OnboardingPager

@Composable
fun OnboardingScreen(
    onComplete: () -> Unit
) {
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
    OnboardingPager(data, onComplete)
}
