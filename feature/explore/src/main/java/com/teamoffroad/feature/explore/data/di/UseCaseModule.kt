package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.feature.explore.domain.repository.LocationRepository
import com.teamoffroad.feature.explore.domain.repository.PlaceRepository
import com.teamoffroad.feature.explore.domain.repository.QuestRepository
import com.teamoffroad.feature.explore.domain.repository.UserRepository
import com.teamoffroad.feature.explore.domain.usecase.GetPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.GetPreviousLocationUseCase
import com.teamoffroad.feature.explore.domain.usecase.GetQuestListUseCase
import com.teamoffroad.feature.explore.domain.usecase.PostExploreLocationAuthUseCase
import com.teamoffroad.feature.explore.domain.usecase.SavePreviousLocationUseCase
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
        placeRepository: PlaceRepository,
    ): GetPlaceListUseCase {
        return GetPlaceListUseCase(placeRepository)
    }

    @Provides
    @Singleton
    fun providePostLocationAuthUseCase(
        userRepository: UserRepository,
    ): PostExploreLocationAuthUseCase {
        return PostExploreLocationAuthUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetQuestListUseCase(
        questRepository: QuestRepository,
    ): GetQuestListUseCase {
        return GetQuestListUseCase(questRepository)
    }

    @Provides
    @Singleton
    fun provideGetPreviousLocationUseCase(
        locationRepository: LocationRepository,
    ): GetPreviousLocationUseCase {
        return GetPreviousLocationUseCase(locationRepository)
    }

    @Provides
    @Singleton
    fun provideSavePreviousLocationUseCase(
        locationRepository: LocationRepository,
    ): SavePreviousLocationUseCase {
        return SavePreviousLocationUseCase(locationRepository)
    }
}
