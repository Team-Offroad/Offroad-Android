package com.teamoffroad.feature.mypage.data.di

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
    fun provideGetCharactersUseCase(
        userRepository: UserRepository,
    ): GetCharactersUseCase {
        return GetCharactersUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserCouponsUseCase(
        userCouponsRepository: MyPageUserRepository,
    ): MyPageUserUseCase {
        return MyPageUserUseCase(userCouponsRepository)
    }

    @Provides
    @Singleton
    fun provideGainedEmblemsUseCase(
        userEmblemsRepository: GainedEmblemsRepository,
    ): UserEmblemsUseCase {
        return UserEmblemsUseCase(userEmblemsRepository)
    }

    @Provides
    @Singleton
    fun provideMarketingInfoUseCase(
        marketingInfoRepository: MyPageUserRepository,
    ): UserMarketingInfoUseCase {
        return UserMarketingInfoUseCase(marketingInfoRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteUserInfoUseCase(
        deleteUserRepository: MyPageUserRepository,
    ): DeleteUserInfoUseCase {
        return DeleteUserInfoUseCase(deleteUserRepository)
    }
}
