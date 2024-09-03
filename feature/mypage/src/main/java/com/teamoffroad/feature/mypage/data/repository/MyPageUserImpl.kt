package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toData
import com.teamoffroad.feature.mypage.data.mapper.toDomain
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
}