package com.teamoffroad.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultAuthPreferencesDataSource @Inject constructor(
    @Named("auth") private val dataStore: DataStore<Preferences>
) : AuthPreferencesDataSource {

    object PreferencesKey {
        val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
        val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
        val AUTO_LOGIN = booleanPreferencesKey("AUTO_LOGIN")
    }

    override val accessToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.ACCESS_TOKEN].orEmpty()
    }

    override val refreshToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.REFRESH_TOKEN].orEmpty()
    }

    override val autoLogin: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.AUTO_LOGIN] ?: false
    }

    override suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun setAutoLogin(autoLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.AUTO_LOGIN] = autoLogin
        }
    }
}