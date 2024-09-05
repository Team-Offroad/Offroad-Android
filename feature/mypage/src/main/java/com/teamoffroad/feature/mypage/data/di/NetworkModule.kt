package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.data.remote.service.UserCouponsService
import com.teamoffroad.feature.mypage.data.remote.service.UserService
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
    fun provideUserCoupons(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserCoupons(retrofit: Retrofit): UserCouponsService {
        return retrofit.create(UserCouponsService::class.java)
    }
}