package com.gpillaca.gohab.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.gpillaca.gohab.onboarding.data.repository.OnboardingRepositoryImpl
import com.gpillaca.gohab.onboarding.domain.repository.OnboardingRepository
import com.gpillaca.gohab.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.gpillaca.gohab.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class OnboardingModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("gohab_preference", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(repository: OnboardingRepository): CompleteOnboardingUseCase {
        return CompleteOnboardingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideHasSeenOnboardingUseCase(repository: OnboardingRepository): HasSeenOnboardingUseCase {
        return HasSeenOnboardingUseCase(repository)
    }

}
