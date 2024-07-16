package com.teamoffroad.feature.auth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.teamoffroad.feature.auth.data.mapper.toDomain
import com.teamoffroad.feature.auth.data.remote.request.SignInInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.service.AuthService
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserToken
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    @Named("authDataStore") private val dataStore: DataStore<Preferences>,
) : AuthRepository {

    override suspend fun signIn(signInInfo: SignInInfo): UserToken {
        val requestDto = SignInInfoRequestDto(
            socialPlatform = signInInfo.socialPlatform,
            name = signInInfo.name,
            code = signInInfo.code
        )
        val response = authService.postSignInInfo(requestDto)
        return response.data?.toDomain() ?: UserToken("", "")
    }

    override val isAutoSignInEnabled: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[AUTO_LOGIN_KEY] ?: false
        }

    override suspend fun setAutoSignInEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[AUTO_LOGIN_KEY] = enabled
        }
    }

    override suspend fun clearAutoSignIn() {
        dataStore.edit { preferences ->
            preferences.remove(AUTO_LOGIN_KEY)
        }
    }

    companion object {
        private val AUTO_LOGIN_KEY = booleanPreferencesKey("auto_login")
    }
}
