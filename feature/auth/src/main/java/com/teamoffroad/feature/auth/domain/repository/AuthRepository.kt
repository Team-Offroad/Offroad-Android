package com.teamoffroad.feature.auth.domain.repository

import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(socialPlatform: String, name: String?, code: String): SignInInfo
    val isAutoSignInEnabled: Flow<Boolean>
    suspend fun setAutoSignInEnabled(enabled: Boolean)
    suspend fun clearAutoSignIn()
    suspend fun getDuplicateNickname(nickname: String): Boolean
    suspend fun getCharacters(): List<Character>
    suspend fun setCharacter(characterId: Int): String
    suspend fun patchUserProfile(userProfile: UserProfile)
    suspend fun clearDataStore()
    suspend fun patchMarketingAgree(marketingAgree: Boolean): Result<Unit>
}
