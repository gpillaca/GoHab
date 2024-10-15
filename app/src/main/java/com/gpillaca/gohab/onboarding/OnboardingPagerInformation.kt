package com.gpillaca.gohab.onboarding

import androidx.annotation.DrawableRes

data class OnboardingPagerInformation(
    val title: String,
    val message: String,
    @DrawableRes val image: Int
)

