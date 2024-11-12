package com.teamoffroad.core.common.data.di

import com.teamoffroad.core.common.domain.repository.AutoSignInRepository
import com.teamoffroad.core.common.domain.repository.TokenRepository
import com.teamoffroad.core.common.domain.usecase.ClearTokensUseCase
import com.teamoffroad.core.common.domain.usecase.GetAccessTokenUseCase
import com.teamoffroad.core.common.domain.usecase.GetAutoSignInUseCase
import com.teamoffroad.core.common.domain.usecase.GetRefreshTokenUseCase
import com.teamoffroad.core.common.domain.usecase.RefreshTokenUseCase
import com.teamoffroad.core.common.domain.usecase.SaveAccessTokenUseCase
import com.teamoffroad.core.common.domain.usecase.SaveRefreshTokenUseCase
import com.teamoffroad.core.common.domain.usecase.SetAutoSignInUseCase
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
    fun provideRefreshTokenUseCase(
        tokenRepository: TokenRepository,
    ): RefreshTokenUseCase {
        return RefreshTokenUseCase(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideSaveAccessTokenUseCase(
        tokenRepository: TokenRepository,
    ): SaveAccessTokenUseCase {
        return SaveAccessTokenUseCase(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideSaveRefreshTokenUseCase(
        tokenRepository: TokenRepository,
    ): SaveRefreshTokenUseCase {
        return SaveRefreshTokenUseCase(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideGetAccessTokenUseCase(
        tokenRepository: TokenRepository,
    ): GetAccessTokenUseCase {
        return GetAccessTokenUseCase(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideGetRefreshTokenUseCase(
        tokenRepository: TokenRepository,
    ): GetRefreshTokenUseCase {
        return GetRefreshTokenUseCase(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideClearTokensUseCase(
        tokenRepository: TokenRepository,
    ): ClearTokensUseCase {
        return ClearTokensUseCase(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideGetAutoLoginUseCase(
        autoSignInRepository: AutoSignInRepository,
    ): GetAutoSignInUseCase {
        return GetAutoSignInUseCase(autoSignInRepository)
    }

    @Provides
    @Singleton
    fun provideSetAutoLoginUseCase(
        autoSignInRepository: AutoSignInRepository,
    ): SetAutoSignInUseCase {
        return SetAutoSignInUseCase(autoSignInRepository)
    }
}
