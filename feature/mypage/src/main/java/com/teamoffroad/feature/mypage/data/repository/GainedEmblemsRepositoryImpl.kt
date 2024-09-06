package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.remote.response.toEmblemsList
import com.teamoffroad.feature.mypage.data.remote.service.EmblemService
import com.teamoffroad.feature.mypage.domain.model.GainedEmblems
import com.teamoffroad.feature.mypage.domain.repository.GainedEmblemsRepository
import javax.inject.Inject

class GainedEmblemsRepositoryImpl @Inject constructor(
    private val userService: EmblemService
) : GainedEmblemsRepository {

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
}

data class UnReachableException(
    val msg: String
) : Exception()