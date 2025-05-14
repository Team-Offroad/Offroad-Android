package com.teamoffroad.feature.recommendplace.data.di

import com.teamoffroad.feature.recommendplace.data.repository.PlaceRecommendationsImpl
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPlaceRecommendationsRepository(
        placeRecommendationsRepositoryImpl: PlaceRecommendationsImpl,
    ): PlaceRecommendationsRepository
}
