package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.feature.explore.data.repository.PlaceRepositoryImpl
import com.teamoffroad.feature.explore.data.repository.UserRepositoryImpl
import com.teamoffroad.feature.explore.domain.repository.PlaceRepository
import com.teamoffroad.feature.explore.domain.repository.UserRepository
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
    abstract fun bindPlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl,
    ): PlaceRepository
}
