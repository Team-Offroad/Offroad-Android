package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.data.remote.service.MyPageUserService
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
    fun provideUserCoupons(retrofit: Retrofit): MyPageUserService {
        return retrofit.create(MyPageUserService::class.java)
    }
}