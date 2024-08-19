package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.data.remote.service.UserCouponsService
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
    fun provideUserCoupons(retrofit: Retrofit): UserCouponsService {
        return retrofit.create(UserCouponsService::class.java)
    }
}
