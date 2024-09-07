package com.teamoffroad.feature.auth.data.di

import com.teamoffroad.feature.auth.data.datasource.AuthPreferencesDataSource
import com.teamoffroad.feature.auth.data.datasource.DefaultAuthPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsTokenLocalDataSource(
        dataSource: DefaultAuthPreferencesDataSource,
    ): AuthPreferencesDataSource
}
