package pe.geff.gohab.authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.geff.gohab.authentication.data.DataAuthenticationRepository
import pe.geff.gohab.authentication.domain.AuthenticationRepository
import pe.geff.gohab.authentication.data.DataEmailValidator
import pe.geff.gohab.authentication.domain.EmailValidator
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthenticationModule {

    @Singleton
    @Provides
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return DataAuthenticationRepository()
    }

    @Singleton
    @Provides
    fun provideEmailValidator(): EmailValidator {
        return DataEmailValidator()
    }
}
