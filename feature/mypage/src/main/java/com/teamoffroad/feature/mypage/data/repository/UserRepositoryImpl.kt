package com.teamoffroad.feature.mypage.data.repository

import android.util.Log
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
            Log.d("asdasd", "success")

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
            Log.d("asdasd", it.message.toString())
            return Result.failure(it)
        }
        return Result.failure(UnReachableException("unreachable code"))
    }
}

data class UnReachableException(
    val msg: String
) : Exception()