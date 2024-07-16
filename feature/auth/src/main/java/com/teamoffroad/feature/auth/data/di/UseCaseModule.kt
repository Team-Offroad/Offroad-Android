package com.teamoffroad.feature.auth.data.di

import com.teamoffroad.feature.auth.domain.usecase.GetValidateNicknameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetValidateNicknameUseCase(): GetValidateNicknameUseCase =
        GetValidateNicknameUseCase()
}