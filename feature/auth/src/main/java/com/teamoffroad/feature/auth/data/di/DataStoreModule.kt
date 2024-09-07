package com.teamoffroad.feature.auth.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.teamoffroad.core.common.data.di.DataStoreModule.createDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    @Named("authDataStore")
    fun provideAuthDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(AUTH_PREFERENCES)
    }

    private const val AUTH_PREFERENCES = "com.teamoffroad.auth_preferences"
}
