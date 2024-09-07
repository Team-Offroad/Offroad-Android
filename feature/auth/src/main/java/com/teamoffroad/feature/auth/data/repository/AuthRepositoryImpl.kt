package com.teamoffroad.feature.auth.data.repository

import com.teamoffroad.feature.auth.data.datasource.AuthPreferencesDataSource
import com.teamoffroad.feature.auth.data.mapper.toData
import com.teamoffroad.feature.auth.data.mapper.toDomain
import com.teamoffroad.feature.auth.data.remote.request.SignInInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.service.AuthService
import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserProfile
import com.teamoffroad.feature.auth.domain.model.UserToken
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val authPreferencesDataSource: AuthPreferencesDataSource,
) : AuthRepository {

    override suspend fun signIn(socialPlatform: String, name: String?, code: String): SignInInfo {
        val requestDto = SignInInfoRequestDto(
            socialPlatform = socialPlatform,
            name = name,
            code = code
        )
        val response = authService.postSignInInfo(requestDto)
        return response.data?.toDomain() ?: SignInInfo(tokens = UserToken("", ""), isAlreadyExist = false)
    }

    override val isAutoSignInEnabled: Flow<Boolean> = authPreferencesDataSource.autoLogin

    override suspend fun setAutoSignInEnabled(enabled: Boolean) {
        authPreferencesDataSource.setAutoLogin(enabled)
    }

    override suspend fun clearAutoSignIn() {
        authPreferencesDataSource.setAutoLogin(false)
    }

    override suspend fun getDuplicateNickname(nickname: String): Boolean =
        authService.getDuplicateNickname(nickname).data?.isDuplicate ?: false

    override suspend fun getCharacters(): List<Character> {
        return authService.getCharacters().data?.characters?.map {
            it.toDomain()
        } ?: emptyList()
    }

    override suspend fun setCharacter(characterId: Int): String {
        return authService.setCharacter(characterId).data.toString()
    }

    override suspend fun patchUserProfile(userProfile: UserProfile) {
        authService.fetchUserProfile(userProfile.toData())
    }
}
