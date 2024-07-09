package com.teamoffroad.feature.auth.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
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

    private val Context.authDataStore by preferencesDataStore("AUTH_DATASTORE_NAME")

    @Provides
    @Singleton
    @Named("auth")
    fun providesAuthDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> =
        context.authDataStore
}