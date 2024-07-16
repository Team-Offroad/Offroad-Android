package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserToken
import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class AuthUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(signInInfo: SignInInfo): UserToken {
        return authRepository.signIn(signInInfo)
    }
}
