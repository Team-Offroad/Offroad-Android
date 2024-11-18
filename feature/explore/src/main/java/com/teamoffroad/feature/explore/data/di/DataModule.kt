package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.feature.explore.data.datasource.DefaultLocationPreferencesDataSource
import com.teamoffroad.feature.explore.data.datasource.LocationPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsLocationLocalDataSource(
        dataSource: DefaultLocationPreferencesDataSource,
    ): LocationPreferencesDataSource
}
