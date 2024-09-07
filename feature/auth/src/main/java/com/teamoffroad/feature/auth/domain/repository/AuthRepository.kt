package com.teamoffroad.feature.auth.domain.repository

import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun performSignIn(socialPlatform: String, name: String?, code: String): SignInInfo
    val isAutoSignInEnabled: Flow<Boolean>
    suspend fun updateAutoSignInEnabled(enabled: Boolean)
    suspend fun fetchDuplicateNickname(nickname: String): Boolean
    suspend fun fetchCharacters(): List<Character>
    suspend fun saveCharacter(characterId: Int): String
    suspend fun saveUserProfile(userProfile: UserProfile)
    suspend fun saveMarketingAgree(marketingAgree: Boolean): Result<Unit>
}
