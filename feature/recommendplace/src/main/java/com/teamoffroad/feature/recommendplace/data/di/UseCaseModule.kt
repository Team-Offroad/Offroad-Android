package com.teamoffroad.feature.recommendplace.data.di

import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import com.teamoffroad.feature.recommendplace.domain.usecase.GetPlaceRecommendationsUseCase
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
    fun provideGetPlaceRecommendationsUseCase(
        placeRecommendationsRepository: PlaceRecommendationsRepository,
    ): GetPlaceRecommendationsUseCase {
        return GetPlaceRecommendationsUseCase(placeRecommendationsRepository)
    }
}
