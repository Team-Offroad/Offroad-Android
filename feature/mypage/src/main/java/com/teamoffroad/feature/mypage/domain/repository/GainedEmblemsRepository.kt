package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.GainedEmblem

interface GainedEmblemsRepository {
    suspend fun getGainedEmblems(): Result<List<GainedEmblem>?>
}