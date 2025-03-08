package com.teamoffroad.feature.diary.data.di

import com.teamoffroad.feature.diary.data.repository.DiarySettingRepositoryImpl
import com.teamoffroad.feature.diary.domain.repository.DiarySettingRepository
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
    abstract fun bindDiarySettingRepository(
        diarySettingRepositoryImpl: DiarySettingRepositoryImpl,
    ): DiarySettingRepository
}