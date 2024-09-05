package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.GainedEmblems

interface UserRepository {
    suspend fun getGainedEmblems(): Result<List<GainedEmblems>?>
    suspend fun patchMarketingInfo(): Result<Unit>
}