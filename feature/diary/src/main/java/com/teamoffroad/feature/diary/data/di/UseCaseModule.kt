package com.teamoffroad.feature.diary.data.di

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryByDateUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryCheckLatestUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryCreateTimeCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryFirstDateUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryLatestUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryMonthlyHexUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryTutorialCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.PatchDiaryCheckUseCase
import com.teamoffroad.feature.diary.domain.usecase.PatchDiaryCreateTimeCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.PatchDiaryCreateTimeUseCase
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
        diaryRepository: DiaryRepository,
    ): GetDiaryTutorialCheckedUseCase {
        return GetDiaryTutorialCheckedUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun providePatchDiaryTutorialCheckedUseCase(
        diaryRepository: DiaryRepository,
    ): PatchDiaryTutorialCheckedUseCase {
        return PatchDiaryTutorialCheckedUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun provideGetDiaryCreateTimeCheckedUseCase(
        diaryRepository: DiaryRepository,
    ): GetDiaryCreateTimeCheckedUseCase {
        return GetDiaryCreateTimeCheckedUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun providePatchDiaryCreateTimeCheckedUseCase(
        diaryRepository: DiaryRepository,
    ): PatchDiaryCreateTimeCheckedUseCase {
        return PatchDiaryCreateTimeCheckedUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun provideGetDiaryFirstDateUseCaseUseCase(
        diaryRepository: DiaryRepository,
    ): GetDiaryFirstDateUseCase {
        return GetDiaryFirstDateUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun provideGetDiaryMonthlyHexUseCase(
        diaryRepository: DiaryRepository,
    ): GetDiaryMonthlyHexUseCase {
        return GetDiaryMonthlyHexUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun provideGetDiaryByDateUseCase(
        diaryRepository: DiaryRepository,
    ): GetDiaryByDateUseCase {
        return GetDiaryByDateUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun provideGetDiaryLatestUseCase(
        diaryRepository: DiaryRepository,
    ): GetDiaryLatestUseCase {
        return GetDiaryLatestUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun providePatchDiaryCreateTimeUseCase(
        diaryRepository: DiaryRepository,
    ): PatchDiaryCreateTimeUseCase {
        return PatchDiaryCreateTimeUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun provideGetDiaryCheckLatestUseCase(
        diaryRepository: DiaryRepository,
    ): GetDiaryCheckLatestUseCase {
        return GetDiaryCheckLatestUseCase(diaryRepository)
    }

    @Provides
    @Singleton
    fun providePatchDiaryCheckUseCase(
        diaryRepository: DiaryRepository,
    ): PatchDiaryCheckUseCase {
        return PatchDiaryCheckUseCase(diaryRepository)
    }
}