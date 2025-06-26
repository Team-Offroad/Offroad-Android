package com.teamoffroad.core.common.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.teamoffroad.core.common.data.preferences.AutoSignInPreferencesImpl.Companion.AUTH_PREFERENCES_NAME
import com.teamoffroad.core.common.data.preferences.TokenPreferencesImpl.Companion.TOKEN_DATA_STORE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    fun Context.createDataStore(preferencesName: String): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            corruptionHandler =
                ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() },
                ),
            migrations = listOf(SharedPreferencesMigration(this, preferencesName)),
            produceFile = { this.preferencesDataStoreFile(preferencesName) },
        )

    @Provides
    @Singleton
    @Named(TOKEN_DATA_STORE_NAME)
    fun provideTokenDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.createDataStore(TOKEN_PREFERENCES)

    @Provides
    @Singleton
    @Named(AUTH_PREFERENCES_NAME)
    fun provideAuthDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.createDataStore(AUTH_PREFERENCES)

    @Provides
    @Singleton
    fun provideDeviceTokenDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.createDataStore(DEVICE_TOKEN_PREFERENCES)

    private const val TOKEN_PREFERENCES = "com.teamoffroad.token_preferences"
    private const val AUTH_PREFERENCES = "com.teamoffroad.auth_preferences"
    private const val DEVICE_TOKEN_PREFERENCES = "com.teamoffroad.device_token_preferences"
}
