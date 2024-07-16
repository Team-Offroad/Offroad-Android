package com.teamoffroad.feature.home.domain.repository

import com.teamoffroad.feature.home.domain.model.Emblem

interface EmblemRepository {
    suspend fun getEmblems(token: String): List<Emblem>
}