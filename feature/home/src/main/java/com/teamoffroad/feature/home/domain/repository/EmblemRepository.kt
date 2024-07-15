package com.teamoffroad.feature.home.domain.repository

import com.teamoffroad.feature.home.domain.model.Emblems

interface EmblemRepository {
    suspend fun getEmblems(token: String): Emblems
}