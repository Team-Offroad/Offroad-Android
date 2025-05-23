package com.teamoffroad.feature.recommendplace.data.di

import com.teamoffroad.feature.recommendplace.data.repository.PlaceRecommendationsRepositoryImpl
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
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
    abstract fun bindPlaceRecommendationsRepository(
        placeRecommendationsRepositoryImpl: PlaceRecommendationsRepositoryImpl,
    ): PlaceRecommendationsRepository
}
