package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.remote.request.DeleteUserInfoRequestDto
import com.teamoffroad.feature.mypage.data.remote.request.MarketingInfoRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.toEmblemsList
import com.teamoffroad.feature.mypage.data.remote.service.UserService
import com.teamoffroad.feature.mypage.domain.model.GainedEmblems
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getGainedEmblems(): Result<List<GainedEmblems>?> {
        val gainedEmblemResult = runCatching { userService.getGainedEmblems().data }
        gainedEmblemResult.onSuccess { gainedEmblemsResponseDto ->
            val a = gainedEmblemsResponseDto?.gainedEmblems?.map {
                it.toEmblemsList()
            }
            val b = gainedEmblemsResponseDto?.notGainedEmblems?.map {
                it.toEmblemsList()
            }
            val list = a?.plus(b!!)
            return Result.success(list)
        }

        gainedEmblemResult.onFailure {
            return Result.failure(it)
        }
        return Result.failure(UnReachableException("unreachable code"))
    }

    override suspend fun patchMarketingInfo(): Result<Unit> {
        val patchMarketingInfoResult = runCatching {
            userService.patchMarketingInfo(
                MarketingInfoRequestDto(true)
            ).data
        }
        patchMarketingInfoResult.onSuccess {}
        patchMarketingInfoResult.onFailure {}
        return Result.failure(UnReachableException("unreachable code"))
    }

    override suspend fun deleteUserInfo(deleteCode: String): Result<Unit> {
        val deleteUserInfo =
            runCatching { userService.deleteUserInfo(DeleteUserInfoRequestDto(deleteCode)) }
        deleteUserInfo.onSuccess {}
        deleteUserInfo.onFailure {}
        return Result.failure(UnReachableException("unreachable code"))
    }
}

data class UnReachableException(
    val msg: String
) : Exception()