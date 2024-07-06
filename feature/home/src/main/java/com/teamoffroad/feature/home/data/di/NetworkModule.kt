package com.teamoffroad.feature.home.data.di

import com.teamoffroad.feature.home.data.remote.service.DummyUserService
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
    fun provideUserService(retrofit: Retrofit): DummyUserService {
        return retrofit.create(DummyUserService::class.java)
    }
}
