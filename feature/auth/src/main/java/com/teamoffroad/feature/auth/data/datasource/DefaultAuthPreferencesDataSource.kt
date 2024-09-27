package com.teamoffroad.feature.auth.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.teamoffroad.feature.auth.domain.model.SocialSignInPlatform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultAuthPreferencesDataSource @Inject constructor(
    @Named("authDataStore") private val dataStore: DataStore<Preferences>,
) : AuthPreferencesDataSource {

    object PreferencesKey {
        val AUTO_LOGIN = stringPreferencesKey("AUTO_LOGIN")
    }

    override val autoLogin: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.AUTO_LOGIN] ?: SocialSignInPlatform.EMPTY.name
    }

    override suspend fun setAutoLogin(socialPlatform: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.AUTO_LOGIN] = socialPlatform
        }
    }
}
