package com.teamoffroad.feature.auth.domain.repository

import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserToken

interface AuthRepository {
    suspend fun signIn(signInInfo: SignInInfo): UserToken
}
