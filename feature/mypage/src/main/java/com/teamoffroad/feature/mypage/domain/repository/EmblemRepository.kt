package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.GainedEmblem

interface EmblemRepository {
    suspend fun getGainedEmblems(): Result<List<GainedEmblem>?>
}