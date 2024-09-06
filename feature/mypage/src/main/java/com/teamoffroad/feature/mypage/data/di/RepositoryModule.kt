package com.teamoffroad.feature.mypage.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindUserCouponsRepository(
        userCouponsRepositoryImpl: MyPageUserImpl
    ): MyPageUserRepository

    @Binds
    @Singleton
    abstract fun bindUserEmblemsRepository(
        userEmblemsRepositoryImpl: GainedEmblemsRepositoryImpl
    ): GainedEmblemsRepository
}