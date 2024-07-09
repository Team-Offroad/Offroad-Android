package com.teamoffroad.feature.auth.data.di

import com.teamoffroad.feature.auth.data.datasource.AuthPreferencesDataSource
import com.teamoffroad.feature.auth.data.datasource.DefaultAuthPreferencesDataSource
import com.teamoffroad.feature.auth.data.repository.AuthInfoRepository
import com.teamoffroad.feature.auth.data.repositoryimpl.AuthInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    @Singleton
    @Named("auth_datasource")
    abstract fun bindsAuthDataSource(
        dataSource: DefaultAuthPreferencesDataSource
    ): AuthPreferencesDataSource

    @Binds
    @Singleton
    @Named("auth_repository")
    abstract fun bindsAuthInfoRepository(
        repository: AuthInfoRepositoryImpl
    ): AuthInfoRepository
}