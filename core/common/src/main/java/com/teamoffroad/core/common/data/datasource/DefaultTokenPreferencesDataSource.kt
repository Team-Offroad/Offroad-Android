package com.teamoffroad.core.common.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultTokenPreferencesDataSource @Inject constructor(
    @Named("tokenDataStore") private val dataStore: DataStore<Preferences>,
) : TokenPreferencesDataSource {

    object PreferencesKey {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("ACCESS_TOKEN_KEY")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("REFRESH_TOKEN_KEY")
        val DEVICE_TOKEN_KEY = stringPreferencesKey("DEVICE_TOKEN_KEY")
    }

    override val accessToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.ACCESS_TOKEN_KEY].orEmpty()
    }

    override val refreshToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.REFRESH_TOKEN_KEY].orEmpty()
    }

    override val deviceToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.DEVICE_TOKEN_KEY].orEmpty()
    }

    override suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.ACCESS_TOKEN_KEY] = accessToken
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    override suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKey.ACCESS_TOKEN_KEY)
            preferences.remove(PreferencesKey.REFRESH_TOKEN_KEY)
        }
    }

    override suspend fun setDeviceToken(deviceToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.DEVICE_TOKEN_KEY] = deviceToken
        }
    }
}
