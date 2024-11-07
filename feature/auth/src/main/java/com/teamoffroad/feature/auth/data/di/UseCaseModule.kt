package com.teamoffroad.feature.auth.data.di

import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.feature.auth.domain.usecase.AuthUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetBirthDateValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetCharacterListUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetNicknameValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.UpdateCharacterUseCase
import com.teamoffroad.feature.auth.domain.usecase.UserMarketingAgreeUseCase
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
    fun provideGetCharacterListUseCase(
        authRepository: AuthRepository,
    ): GetCharacterListUseCase {
        return GetCharacterListUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSetCharacterUseCase(
        authRepository: AuthRepository,
    ): UpdateCharacterUseCase {
        return UpdateCharacterUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetNicknameValidateUseCase(): GetNicknameValidateUseCase {
        return GetNicknameValidateUseCase()
    }

    @Provides
    @Singleton
    fun provideGetBirthDateValidateUseCase(): GetBirthDateValidateUseCase {
        return GetBirthDateValidateUseCase()
    }

    @Provides
    @Singleton
    fun provideMarketingAgreeUseCase(
        authRepository: AuthRepository,
    ): UserMarketingAgreeUseCase {
        return UserMarketingAgreeUseCase(authRepository)
    }
}
