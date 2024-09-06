package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.GainedEmblems

interface GainedEmblemsRepository {
    suspend fun getGainedEmblems(): Result<List<GainedEmblems>?>
}