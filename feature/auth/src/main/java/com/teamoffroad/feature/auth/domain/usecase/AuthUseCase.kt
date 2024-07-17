package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class AuthUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(socialPlatform: String, name: String?, code: String): SignInInfo {
        return authRepository.signIn(socialPlatform, name, code)
    }
}
