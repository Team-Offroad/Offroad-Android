package com.teamoffroad.feature.diary.data.di

import com.teamoffroad.feature.diary.domain.repository.DiarySettingRepository
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryCreateTimeCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryTutorialCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.PatchDiaryCreateTimeCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.PatchDiaryTutorialCheckedUseCase
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
    fun provideGetDiaryTutorialCheckedUseCase(
        diarySettingRepository: DiarySettingRepository,
    ): GetDiaryTutorialCheckedUseCase {
        return GetDiaryTutorialCheckedUseCase(diarySettingRepository)
    }

    @Provides
    @Singleton
    fun providePatchDiaryTutorialCheckedUseCase(
        diarySettingRepository: DiarySettingRepository,
    ): PatchDiaryTutorialCheckedUseCase {
        return PatchDiaryTutorialCheckedUseCase(diarySettingRepository)
    }

    @Provides
    @Singleton
    fun provideGetDiaryCreateTimeCheckedUseCase(
        diarySettingRepository: DiarySettingRepository,
    ): GetDiaryCreateTimeCheckedUseCase {
        return GetDiaryCreateTimeCheckedUseCase(diarySettingRepository)
    }

    @Provides
    @Singleton
    fun providePatchDiaryCreateTimeCheckedUseCase(
        diarySettingRepository: DiarySettingRepository,
    ): PatchDiaryCreateTimeCheckedUseCase {
        return PatchDiaryCreateTimeCheckedUseCase(diarySettingRepository)
    }
}