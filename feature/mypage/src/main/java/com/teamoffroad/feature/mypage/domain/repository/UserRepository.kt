package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.Character

interface UserRepository {

    suspend fun fetchCharacters(): List<Character>
}
