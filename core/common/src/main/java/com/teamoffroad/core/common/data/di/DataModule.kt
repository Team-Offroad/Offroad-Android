package com.teamoffroad.core.common.data.di

import com.teamoffroad.core.common.data.preferences.AutoSignInPreferencesImpl
import com.teamoffroad.core.common.data.preferences.TokenPreferencesImpl
import com.teamoffroad.core.common.domain.preferences.AutoSignInPreferences
import com.teamoffroad.core.common.domain.preferences.TokenPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {
    @Binds
    abstract fun bindsTokenLocalDataSource(dataSource: TokenPreferencesImpl): TokenPreferences

    @Binds
    abstract fun bindsAutoSignInLocalDataSource(dataSource: AutoSignInPreferencesImpl): AutoSignInPreferences
}
