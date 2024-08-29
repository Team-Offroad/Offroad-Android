package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toData
import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.service.MyPageUserService
import com.teamoffroad.feature.mypage.domain.model.MyPageUser
import com.teamoffroad.feature.mypage.domain.repository.MyPageUserRepository
import javax.inject.Inject

class MyPageUserImpl @Inject constructor(
    private val myPageUserService: MyPageUserService
) : MyPageUserRepository {

    override suspend fun getUsersMyPage(): MyPageUser {
        val response = myPageUserService.getMyPageUser()
        val myPageUser = response.data?.toData()
        val domainMyPageUser = myPageUser?.toDomain()
        return domainMyPageUser ?: MyPageUser("", "", 0, 0, 0)
    }
}