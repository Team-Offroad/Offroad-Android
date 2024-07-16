package com.teamoffroad.feature.auth.data.repository

import com.teamoffroad.feature.auth.data.mapper.toDomain
import com.teamoffroad.feature.auth.data.remote.request.SignInInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.service.AuthService
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserToken
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
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
}
