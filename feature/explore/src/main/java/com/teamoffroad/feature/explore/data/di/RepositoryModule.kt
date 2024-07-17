package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.feature.explore.data.repository.ExploreRepositoryImpl
import com.teamoffroad.feature.explore.domain.repository.ExploreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindExploreRepository(
        exploreRepositoryImpl: ExploreRepositoryImpl,
    ): ExploreRepository
}
