package com.teamoffroad.feature.auth.data.di

import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.feature.auth.domain.usecase.AuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(
        authRepository: AuthRepository,
    ): AuthUseCase {
        return AuthUseCase(authRepository)
    }
}
