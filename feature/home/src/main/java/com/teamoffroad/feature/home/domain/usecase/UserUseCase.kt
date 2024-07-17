package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.model.UsersAdventuresInformations
import com.teamoffroad.feature.home.domain.repository.UserRepository

class UserUseCase(
    private val userRepository: UserRepository,
) {
    suspend fun getEmblems(): List<Emblem> {
        return userRepository.getEmblems()
    }

    suspend operator fun invoke(emblemCode: String) {
        return userRepository.patchEmblem(emblemCode)
    }

    suspend fun getUserQuests(): UserQuests {
        return userRepository.getUserQuests()
    }

    suspend fun getUsersAdventuresInformations(category: String): UsersAdventuresInformations {
        return userRepository.getUsersAdventuresInformations(category)
    }
}