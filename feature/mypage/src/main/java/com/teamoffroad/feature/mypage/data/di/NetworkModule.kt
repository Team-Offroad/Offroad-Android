package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.core.common.data.di.qualifier.Auth
import com.teamoffroad.feature.mypage.data.remote.service.AnnouncementService
import com.teamoffroad.feature.mypage.data.remote.service.CharacterService
import com.teamoffroad.feature.mypage.data.remote.service.EmblemService
import com.teamoffroad.feature.mypage.data.remote.service.MotionService
import com.teamoffroad.feature.mypage.data.remote.service.UserCouponService
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
    fun provideUserService(@Auth retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideEmblemService(@Auth retrofit: Retrofit): EmblemService {
        return retrofit.create(EmblemService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserCoupon(@Auth retrofit: Retrofit): UserCouponService {
        return retrofit.create(UserCouponService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterService(@Auth retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    @Provides
    @Singleton
    fun provideMotionService(@Auth retrofit: Retrofit): MotionService {
        return retrofit.create(MotionService::class.java)
    }

    @Provides
    @Singleton
    fun provideAnnouncementService(@Auth retrofit: Retrofit): AnnouncementService {
        return retrofit.create(AnnouncementService::class.java)
    }
}
