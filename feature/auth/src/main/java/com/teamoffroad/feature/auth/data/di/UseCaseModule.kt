package com.teamoffroad.feature.auth.data.di

import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.feature.auth.domain.usecase.AuthUseCase
import com.teamoffroad.feature.auth.domain.usecase.ClearAutoSignInUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetAutoSignInUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetCharacterListUseCase
import com.teamoffroad.feature.auth.domain.usecase.SetAutoSignInUseCase
import com.teamoffroad.feature.auth.domain.usecase.SetCharacterUseCase
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

    @Provides
    @Singleton
    fun provideGetAutoLoginUseCase(
        authRepository: AuthRepository,
    ): GetAutoSignInUseCase {
        return GetAutoSignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSetAutoLoginUseCase(
        authRepository: AuthRepository,
    ): SetAutoSignInUseCase {
        return SetAutoSignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideClearAutoLoginUseCase(
        authRepository: AuthRepository,
    ): ClearAutoSignInUseCase {
        return ClearAutoSignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetCharacterListUseCase(
        authRepository: AuthRepository,
    ): GetCharacterListUseCase {
        return GetCharacterListUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSetCharacterUseCase(
        authRepository: AuthRepository,
    ): SetCharacterUseCase {
        return SetCharacterUseCase(authRepository)
    }
}
