package com.teamoffroad.core.common.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.teamoffroad.core.common.domain.preferences.AutoSignInPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class AutoSignInPreferencesImpl
    @Inject
    constructor(
        @Named(AUTH_PREFERENCES_NAME) private val dataStore: DataStore<Preferences>,
    ) : AutoSignInPreferences {
        object PreferencesKey {
            val AUTO_LOGIN = booleanPreferencesKey("AUTO_LOGIN")
        }

        override val autoLogin: Flow<Boolean> =
            dataStore.data.map { preferences ->
                preferences[PreferencesKey.AUTO_LOGIN] ?: false
            }

        override suspend fun setAutoLogin(autoLogin: Boolean) {
            dataStore.edit { preferences ->
                preferences[PreferencesKey.AUTO_LOGIN] = autoLogin
            }
        }

        companion object {
            const val AUTH_PREFERENCES_NAME = "AUTH_DATA_STORE"
        }
    }
