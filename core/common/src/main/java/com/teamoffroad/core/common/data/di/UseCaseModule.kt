package com.teamoffroad.core.common.data.di

import com.teamoffroad.core.common.domain.repository.AutoSignInRepository
import com.teamoffroad.core.common.domain.repository.MinSupportedVersionRepository
import com.teamoffroad.core.common.domain.repository.QuestRepository
import com.teamoffroad.core.common.domain.repository.TokenRepository
import com.teamoffroad.core.common.domain.usecase.ClearTokensUseCase
import com.teamoffroad.core.common.domain.usecase.GetAccessTokenUseCase
import com.teamoffroad.core.common.domain.usecase.GetAutoSignInUseCase
import com.teamoffroad.core.common.domain.usecase.GetCategoryUseCase
import com.teamoffroad.core.common.domain.usecase.GetCompleteQuestListUseCase
import com.teamoffroad.core.common.domain.usecase.GetRefreshTokenUseCase
import com.teamoffroad.core.common.domain.usecase.MinSupportedVersionUseCase
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
    fun provideRefreshTokenUseCase(tokenRepository: TokenRepository): RefreshTokenUseCase = RefreshTokenUseCase(tokenRepository)

    @Provides
    @Singleton
    fun provideSaveAccessTokenUseCase(tokenRepository: TokenRepository): SaveAccessTokenUseCase = SaveAccessTokenUseCase(tokenRepository)

    @Provides
    @Singleton
    fun provideSaveRefreshTokenUseCase(tokenRepository: TokenRepository): SaveRefreshTokenUseCase = SaveRefreshTokenUseCase(tokenRepository)

    @Provides
    @Singleton
    fun provideGetAccessTokenUseCase(tokenRepository: TokenRepository): GetAccessTokenUseCase = GetAccessTokenUseCase(tokenRepository)

    @Provides
    @Singleton
    fun provideGetRefreshTokenUseCase(tokenRepository: TokenRepository): GetRefreshTokenUseCase = GetRefreshTokenUseCase(tokenRepository)

    @Provides
    @Singleton
    fun provideClearTokensUseCase(tokenRepository: TokenRepository): ClearTokensUseCase = ClearTokensUseCase(tokenRepository)

    @Provides
    @Singleton
    fun provideGetAutoLoginUseCase(autoSignInRepository: AutoSignInRepository): GetAutoSignInUseCase =
        GetAutoSignInUseCase(autoSignInRepository)

    @Provides
    @Singleton
    fun provideSetAutoLoginUseCase(autoSignInRepository: AutoSignInRepository): SetAutoSignInUseCase =
        SetAutoSignInUseCase(autoSignInRepository)

    @Provides
    @Singleton
    fun provideGetMinSupportedVersionUseCase(minSupportedVersionRepository: MinSupportedVersionRepository): MinSupportedVersionUseCase =
        MinSupportedVersionUseCase(minSupportedVersionRepository)

    @Provides
    @Singleton
    fun provideGetCategoryUseCase(questRepository: QuestRepository): GetCategoryUseCase = GetCategoryUseCase(questRepository)

    @Provides
    @Singleton
    fun provideGetCompleteQuestListUseCase(questRepository: QuestRepository): GetCompleteQuestListUseCase =
        GetCompleteQuestListUseCase(questRepository)
}
