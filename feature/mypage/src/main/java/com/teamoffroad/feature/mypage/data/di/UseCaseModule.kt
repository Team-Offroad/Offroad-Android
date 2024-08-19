package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.domain.repository.UserCouponsRepository
import com.teamoffroad.feature.mypage.domain.usecase.UserCouponsUseCase
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
        userCouponsRepository: UserCouponsRepository
    ): UserCouponsUseCase {
        return UserCouponsUseCase(userCouponsRepository)
    }
}