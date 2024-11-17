package com.teamoffroad.core.common.data.di

import com.teamoffroad.core.common.data.datasource.AutoSignInPreferencesDataSource
import com.teamoffroad.core.common.data.datasource.DefaultAutoSignInPreferencesDataSource
import com.teamoffroad.core.common.data.datasource.DefaultDeviceTokenPreferencesDataSource
import com.teamoffroad.core.common.data.datasource.DefaultTokenPreferencesDataSource
import com.teamoffroad.core.common.data.datasource.DeviceTokenPreferencesDataSource
import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsTokenLocalDataSource(
        dataSource: DefaultTokenPreferencesDataSource,
    ): TokenPreferencesDataSource

    @Binds
    abstract fun bindsAutoSignInLocalDataSource(
        dataSource: DefaultAutoSignInPreferencesDataSource,
    ): AutoSignInPreferencesDataSource

    @Binds
    abstract fun bindsDeviceTokenLocalDataSource(
        dataSource: DefaultDeviceTokenPreferencesDataSource,
    ): DeviceTokenPreferencesDataSource
}
