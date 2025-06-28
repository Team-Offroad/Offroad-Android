package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.feature.explore.domain.repository.LocationRepository
import com.teamoffroad.feature.explore.domain.repository.PlaceRepository
import com.teamoffroad.feature.explore.domain.repository.QuestRepository
import com.teamoffroad.feature.explore.domain.repository.UserRepository
import com.teamoffroad.feature.explore.domain.usecase.GetMapPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.GetPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.GetPreviousLocationUseCase
import com.teamoffroad.feature.explore.domain.usecase.GetQuestCourseUseCase
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
    fun provideGetMapPlaceListUseCase(placeRepository: PlaceRepository): GetMapPlaceListUseCase = GetMapPlaceListUseCase(placeRepository)

    @Provides
    @Singleton
    fun provideGetPlaceListUseCase(placeRepository: PlaceRepository): GetPlaceListUseCase = GetPlaceListUseCase(placeRepository)

    @Provides
    @Singleton
    fun providePostLocationAuthUseCase(
        userRepository: UserRepository,
        questRepository: com.teamoffroad.core.common.domain.repository.QuestRepository,
    ): PostExploreLocationAuthUseCase = PostExploreLocationAuthUseCase(userRepository, questRepository)

    @Provides
    @Singleton
    fun provideGetQuestListUseCase(questRepository: QuestRepository): GetQuestListUseCase = GetQuestListUseCase(questRepository)

    @Provides
    @Singleton
    fun provideGetPreviousLocationUseCase(locationRepository: LocationRepository): GetPreviousLocationUseCase =
        GetPreviousLocationUseCase(locationRepository)

    @Provides
    @Singleton
    fun provideSavePreviousLocationUseCase(locationRepository: LocationRepository): SavePreviousLocationUseCase =
        SavePreviousLocationUseCase(locationRepository)

    @Provides
    @Singleton
    fun provideGetQuestCourseUseCase(questRepository: QuestRepository): GetQuestCourseUseCase = GetQuestCourseUseCase(questRepository)
}
