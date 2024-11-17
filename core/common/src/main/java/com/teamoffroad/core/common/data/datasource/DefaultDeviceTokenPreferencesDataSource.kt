package com.teamoffroad.core.common.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultDeviceTokenPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : DeviceTokenPreferencesDataSource {

    object PreferencesKey {
        val DEVICE_TOKEN_KEY = stringPreferencesKey("DEVICE_TOKEN_KEY")
    }

    override val deviceToken: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.DEVICE_TOKEN_KEY].orEmpty()
    }

    override suspend fun setDeviceToken(deviceToken: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.DEVICE_TOKEN_KEY] = deviceToken
        }
    }
}