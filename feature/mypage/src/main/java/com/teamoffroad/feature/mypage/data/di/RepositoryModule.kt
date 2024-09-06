package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.data.repository.MyPageUserImpl
import com.teamoffroad.feature.mypage.domain.repository.MyPageUserRepository
import com.teamoffroad.feature.mypage.data.repository.UserRepositoryImpl
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserCouponsRepository(
        userCouponsRepositoryImpl: MyPageUserImpl
    ): MyPageUserRepository

    @Binds
    @Singleton
    abstract fun bindUserEmblemsRepository(
        userEmblemsRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}