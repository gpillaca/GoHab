package pe.geff.gohab.authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.geff.gohab.authentication.data.DataAuthenticationRepository
import pe.geff.gohab.authentication.domain.AuthenticationRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthenticationModule {

    @Singleton
    @Provides
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return DataAuthenticationRepository()
    }
}
