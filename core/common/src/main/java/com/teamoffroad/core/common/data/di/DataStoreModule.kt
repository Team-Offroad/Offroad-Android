package com.teamoffroad.core.common.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
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

    fun Context.createDataStore(preferencesName: String): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(this, preferencesName)),
            produceFile = { this.preferencesDataStoreFile(preferencesName) }
        )
    }

    @Provides
    @Singleton
    @Named("tokenDataStore")
    fun provideTokenDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(TOKEN_PREFERENCES)
    }

    private const val TOKEN_PREFERENCES = "com.teamoffroad.token_preferences"
}
