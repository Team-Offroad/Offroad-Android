package com.teamoffroad.feature.recommendplace.data.di

import com.teamoffroad.core.common.data.di.qualifier.Auth
import com.teamoffroad.core.common.data.di.qualifier.NoneAuth
import com.teamoffroad.feature.recommendplace.data.remote.service.PlaceRecommendationsService
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
    fun providePlaceRecommendationsService(@Auth retrofit: Retrofit): PlaceRecommendationsService {
        return retrofit.create(PlaceRecommendationsService::class.java)
    }
}
