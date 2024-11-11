package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.model.ValidateResult
import java.time.LocalDate
import javax.inject.Inject

class YearValidateUseCase @Inject constructor() {
    operator fun invoke(year: String): ValidateResult {
        return when (year.toIntOrNull()) {
            null -> {
                ValidateResult.EMPTY
            }

            else -> {
                if (year.toInt() in 1900..LocalDate.now().year) {
                    ValidateResult.SUCCESS
                } else {
                    ValidateResult.WRONGRANGE
                }
            }
        }
    }
}