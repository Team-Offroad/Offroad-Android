package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toGainedEmblemsList
import com.teamoffroad.feature.mypage.data.mapper.toNotGainedEmblemsList
import com.teamoffroad.feature.mypage.data.remote.service.EmblemService
import com.teamoffroad.feature.mypage.domain.model.GainedEmblem
import com.teamoffroad.feature.mypage.domain.repository.GainedEmblemsRepository
import javax.inject.Inject

class GainedEmblemsRepositoryImpl @Inject constructor(
    private val emblemService: EmblemService
) : GainedEmblemsRepository {

    override suspend fun getGainedEmblems(): Result<List<GainedEmblem>?> {
        val gainedEmblemResult = runCatching { emblemService.getGainedEmblems().data }
        gainedEmblemResult.onSuccess { gainedEmblemsResponseDto ->
            val gainedEmblemsList = gainedEmblemsResponseDto?.gainedEmblemResponseDtos?.map {
                it.toGainedEmblemsList()
            }
            val notGainedEmblemsList = gainedEmblemsResponseDto?.notGainedEmblemResponseDtos?.map {
                it.toNotGainedEmblemsList()
            }
            val list = gainedEmblemsList?.plus(notGainedEmblemsList!!)
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