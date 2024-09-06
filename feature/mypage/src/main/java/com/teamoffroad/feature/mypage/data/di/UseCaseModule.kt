package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.domain.repository.MyPageUserRepository
import com.teamoffroad.feature.mypage.domain.usecase.MyPageUserUseCase
import com.teamoffroad.feature.mypage.domain.repository.GainedEmblemsRepository
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
import com.teamoffroad.feature.mypage.domain.usecase.DeleteUserInfoUseCase
import com.teamoffroad.feature.mypage.domain.usecase.UserEmblemsUseCase
import com.teamoffroad.feature.mypage.domain.usecase.UserMarketingInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUserCouponsUseCase(
        userCouponsRepository: MyPageUserRepository
    ): MyPageUserUseCase {
        return MyPageUserUseCase(userCouponsRepository)
    }

    @Provides
    @Singleton
    fun provideGainedEmblemsUseCase(
        userEmblemsRepository: GainedEmblemsRepository
    ): UserEmblemsUseCase {
        return UserEmblemsUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideMarketingInfoUseCase(
        userRepository: UserRepository
    ): UserMarketingInfoUseCase {
        return UserMarketingInfoUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteUserInfoUseCase(
        userRepository: UserRepository
    ): DeleteUserInfoUseCase {
        return DeleteUserInfoUseCase(userRepository)
    }
}