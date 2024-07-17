package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.feature.explore.domain.repository.ExploreRepository
import com.teamoffroad.feature.explore.domain.usecase.GetPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.PostExploreAuthUseCase
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
    fun postExploreAuthenticationUseCase(
        exploreRepository: ExploreRepository,
    ): PostExploreAuthUseCase {
        return PostExploreAuthUseCase(exploreRepository)
    }
}
