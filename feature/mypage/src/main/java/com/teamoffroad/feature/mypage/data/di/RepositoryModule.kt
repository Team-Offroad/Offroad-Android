package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.data.repository.AnnouncementRepositoryImpl
import com.teamoffroad.feature.mypage.data.repository.CharacterRepositoryImpl
import com.teamoffroad.feature.mypage.data.repository.EmblemRepositoryImpl
import com.teamoffroad.feature.mypage.data.repository.MotionRepositoryImpl
import com.teamoffroad.feature.mypage.data.repository.UserCouponRepositoryImpl
import com.teamoffroad.feature.mypage.data.repository.UserRepositoryImpl
import com.teamoffroad.feature.mypage.domain.repository.AnnouncementRepository
import com.teamoffroad.feature.mypage.domain.repository.CharacterRepository
import com.teamoffroad.feature.mypage.domain.repository.EmblemRepository
import com.teamoffroad.feature.mypage.domain.repository.MotionRepository
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
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
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    abstract fun bindEmblemRepository(
        emblemRepositoryImpl: EmblemRepositoryImpl,
    ): EmblemRepository

    @Binds
    @Singleton
    abstract fun bindUserCouponsRepository(
        userCouponsRepositoryImpl: UserCouponRepositoryImpl,
    ): UserCouponRepository

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl,
    ): CharacterRepository

    @Binds
    abstract fun bindMotionRepository(
        motionRepositoryImpl: MotionRepositoryImpl,
    ): MotionRepository

    @Binds
    abstract fun bindAnnouncementRepository(
        announcementRepositoryImpl: AnnouncementRepositoryImpl,
    ): AnnouncementRepository
}
