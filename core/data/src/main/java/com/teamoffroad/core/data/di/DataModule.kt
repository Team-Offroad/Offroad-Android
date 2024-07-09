package com.teamoffroad.core.data.di

import com.teamoffroad.core.data.repository.AuthInfoRepository
import com.teamoffroad.core.data.repositoryimpl.AuthInfoRepositoryImpl
import com.teamoffroad.core.datastore.datasource.AuthPreferencesDataSource
import com.teamoffroad.core.datastore.datasource.DefaultAuthPreferencesDataSource
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