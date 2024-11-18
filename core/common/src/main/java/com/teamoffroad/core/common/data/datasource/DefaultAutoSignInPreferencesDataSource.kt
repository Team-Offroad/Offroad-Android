package com.teamoffroad.core.common.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultAutoSignInPreferencesDataSource @Inject constructor(
    @Named("authDataStore") private val dataStore: DataStore<Preferences>,
) : AutoSignInPreferencesDataSource {

    object PreferencesKey {
        val AUTO_LOGIN = booleanPreferencesKey("AUTO_LOGIN")
    }

    override val autoLogin: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.AUTO_LOGIN] ?: false
    }

    override suspend fun setAutoLogin(autoLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.AUTO_LOGIN] = autoLogin
        }
    }
}
