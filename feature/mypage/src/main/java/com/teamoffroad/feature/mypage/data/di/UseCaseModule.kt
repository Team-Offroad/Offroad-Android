package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.domain.repository.CharacterRepository
import com.teamoffroad.feature.mypage.domain.repository.EmblemRepository
import com.teamoffroad.feature.mypage.domain.repository.MotionRepository
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
import com.teamoffroad.feature.mypage.domain.usecase.DeleteUserInfoUseCase
import com.teamoffroad.feature.mypage.domain.usecase.GetCharacterDetailUseCase
import com.teamoffroad.feature.mypage.domain.usecase.GetCharacterListUseCase
import com.teamoffroad.feature.mypage.domain.usecase.GetCharacterMotionListUseCase
import com.teamoffroad.feature.mypage.domain.usecase.GetMyPageUserUseCase
import com.teamoffroad.feature.mypage.domain.usecase.GetUserEmblemListUseCase
import com.teamoffroad.feature.mypage.domain.usecase.UserCouponListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCharacterListUseCase(
        userRepository: UserRepository,
    ): GetCharacterListUseCase {
        return GetCharacterListUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetMyPageUserUseCase(
        userRepository: UserRepository,
    ): GetMyPageUserUseCase {
        return GetMyPageUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideUserEmblemListUseCase(
        userEmblemListRepository: EmblemRepository,
    ): GetUserEmblemListUseCase {
        return GetUserEmblemListUseCase(userEmblemListRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteUserInfoUseCase(
        userRepository: UserRepository,
    ): DeleteUserInfoUseCase {
        return DeleteUserInfoUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailUseCase(
        characterRepository: CharacterRepository,
    ): GetCharacterDetailUseCase {
        return GetCharacterDetailUseCase(characterRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserCouponUseCase(
        userCouponRepository: UserCouponRepository,
    ): UserCouponListUseCase {
        return UserCouponListUseCase(userCouponRepository)
    }

    @Provides
    @Singleton
    fun provideGetCharacterMotionListUseCase(
        motionRepository: MotionRepository,
    ): GetCharacterMotionListUseCase {
        return GetCharacterMotionListUseCase(motionRepository)
    }
}
