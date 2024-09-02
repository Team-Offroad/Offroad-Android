package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.data.repository.UserRepositoryImpl
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
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
}
