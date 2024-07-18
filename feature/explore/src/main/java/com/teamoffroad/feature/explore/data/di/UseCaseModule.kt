package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.feature.explore.domain.repository.ExploreRepository
import com.teamoffroad.feature.explore.domain.usecase.GetPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.PostExploreLocationAuthUseCase
import com.teamoffroad.feature.explore.domain.usecase.PostExploreQrAuthUseCase
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
    fun provideGetPlaceListUseCase(
        exploreRepository: ExploreRepository,
    ): GetPlaceListUseCase {
        return GetPlaceListUseCase(exploreRepository)
    }

    @Provides
    @Singleton
    fun provideQrAuthUseCase(
        exploreRepository: ExploreRepository,
    ): PostExploreQrAuthUseCase {
        return PostExploreQrAuthUseCase(exploreRepository)
    }

    @Provides
    @Singleton
    fun provideLocationAuthUseCase(
        exploreRepository: ExploreRepository,
    ): PostExploreLocationAuthUseCase {
        return PostExploreLocationAuthUseCase(exploreRepository)
    }
}
