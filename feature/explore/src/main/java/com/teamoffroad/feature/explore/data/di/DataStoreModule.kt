package com.teamoffroad.feature.explore.data.di

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
    @Named("locationDataStore")
    fun provideExploreDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(TOKEN_PREFERENCES)
    }

    private const val TOKEN_PREFERENCES = "com.teamoffroad.location_preferences"
}
