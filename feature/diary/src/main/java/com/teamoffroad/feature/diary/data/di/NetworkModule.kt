package com.teamoffroad.feature.diary.data.di

import com.teamoffroad.core.common.data.di.qualifier.Auth
import com.teamoffroad.feature.diary.data.remote.service.DiarySettingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideDiarySettingService(@Auth retrofit: Retrofit): DiarySettingService {
        return retrofit.create(DiarySettingService::class.java)
    }
}