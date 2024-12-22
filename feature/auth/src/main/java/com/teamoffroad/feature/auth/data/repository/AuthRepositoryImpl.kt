package com.teamoffroad.feature.auth.data.repository

import com.teamoffroad.feature.auth.data.mapper.toData
import com.teamoffroad.feature.auth.data.mapper.toDomain
import com.teamoffroad.feature.auth.data.remote.request.SignInInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.request.UserMarketingInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.service.AuthService
import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserProfile
import com.teamoffroad.feature.auth.domain.model.UserToken
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRepository {

    override suspend fun performSignIn(
        socialPlatform: String,
        name: String?,
        code: String
    ): SignInInfo {
        val requestDto = SignInInfoRequestDto(
            socialPlatform = socialPlatform,
            name = name,
            code = code
        )
        val response = authService.postSignInInfo(requestDto)
        return response.data?.toDomain() ?: SignInInfo(
            tokens = UserToken("", ""),
            isAlreadyExist = false
        )
    }

    override suspend fun fetchDuplicateNickname(nickname: String): Boolean =
        authService.getDuplicateNickname(nickname).data?.isDuplicate ?: false

    override suspend fun fetchCharacters(): List<Character> {
        return authService.getCharacters().data?.characters?.map {
            it.toDomain()
        } ?: emptyList()
    }

    override suspend fun saveCharacter(characterId: Int): String {
        return authService.setCharacter(characterId).data.toString()
    }

    override suspend fun saveUserProfile(userProfile: UserProfile): String {
        return authService.fetchUserProfile(userProfile.toData()).data.toString()
    }

    override suspend fun saveMarketingAgree(marketingAgree: Boolean): Result<Unit> {
        val patchMarketingInfoResult = runCatching {
            authService.patchMarketingInfo(UserMarketingInfoRequestDto(marketingAgree)).data
        }
        patchMarketingInfoResult
            .onSuccess {}
            .onFailure {}
        return Result.failure(Exception())
    }
}
