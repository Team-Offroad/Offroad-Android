package com.teamoffroad.core.common.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.teamoffroad.core.common.data.local.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenModule {

    @Provides
    @Singleton
    fun provideTokenManager(@Named("tokenDataStore") dataStore: DataStore<Preferences>): TokenManager {
        return TokenManager(dataStore)
    }
}
