package com.teamoffroad.feature.auth.domain.repository

import com.teamoffroad.core.common.domain.model.Token

interface OAuthInteractor {
    suspend fun signInKakao(): Result<Token>
    suspend fun signInGoogle(): Result<Token>
}