package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.model.ValidateResult
import javax.inject.Inject

class MonthValidateUseCase @Inject constructor() {
    operator fun invoke(month: String): ValidateResult {
        return when (month.toIntOrNull()) {
            null -> {
                ValidateResult.EMPTY
            }

            in 1..12 -> {
                ValidateResult.SUCCESS
            }

            else -> {
                ValidateResult.WRONGRANGE
            }
        }
    }
}