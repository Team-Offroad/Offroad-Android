package com.teamoffroad.feature.home.data.di

import com.teamoffroad.core.common.data.di.qualifier.Auth
import com.teamoffroad.feature.home.data.remote.service.DummyUserService
import com.teamoffroad.feature.home.data.remote.service.UserService
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
    fun provideUserService(@Auth retrofit: Retrofit): DummyUserService {
        return retrofit.create(DummyUserService::class.java)
    }

    @Provides
    @Singleton
    fun provideEmblemService(@Auth retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}
