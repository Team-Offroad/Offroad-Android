package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toData
import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.request.DeleteUserInfoRequestDto
import com.teamoffroad.feature.mypage.data.remote.request.MarketingInfoRequestDto
import com.teamoffroad.feature.mypage.data.remote.service.UserService
import com.teamoffroad.feature.mypage.domain.model.MyPageUser
import com.teamoffroad.feature.mypage.domain.repository.MyPageUserRepository
import javax.inject.Inject

class MyPageUserImpl @Inject constructor(
    private val userService: UserService
) : MyPageUserRepository {

    override suspend fun fetchMyPage(): MyPageUser {
        val myPageResponse = userService.getMyPageUser()
        val domainMyPageUser = myPageResponse.data?.toData()?.toDomain()
        return domainMyPageUser ?: MyPageUser("", "", 0, 0, 0)
    }

    override suspend fun patchMarketingInfo(): Result<Unit> {
        val patchMarketingInfoResult = runCatching {
            userService.patchMarketingInfo(
                MarketingInfoRequestDto(
                    true
                )
            ).data
        }
        patchMarketingInfoResult.onSuccess {
        }
        patchMarketingInfoResult.onFailure {
        }
        return Result.failure(UnReachableException("unreachable code"))
    }

    override suspend fun deleteUserInfo(deleteCode: String): Result<Unit> {
        val deleteUserInfo =
            runCatching { userService.deleteUserInfo(DeleteUserInfoRequestDto(deleteCode)) }
        deleteUserInfo.onSuccess {
        }
        deleteUserInfo.onFailure {
        }
        return Result.failure(UnReachableException("unreachable code"))
    }


}