package com.teamoffroad.feature.auth.domain.repository

import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserToken
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(signInInfo: SignInInfo): UserToken
    val isAutoSignInEnabled: Flow<Boolean>
    suspend fun setAutoSignInEnabled(enabled: Boolean)
    suspend fun clearAutoSignIn()
    suspend fun getDuplicateNickname(nickname: String): Boolean
    suspend fun getCharacters(): List<Character>
}
