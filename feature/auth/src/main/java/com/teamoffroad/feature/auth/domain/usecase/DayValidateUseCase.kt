package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.model.ValidateResult
import javax.inject.Inject

class DayValidateUseCase @Inject constructor() {
    operator fun invoke(year: String, month: String, day: String): ValidateResult {
        if (day.isEmpty()) return ValidateResult.EMPTY
        when (month.toIntOrNull()) {
            null -> {
                return checkValidateDay(31, day.toInt())
            }

            1, 3, 5, 7, 8, 10, 12 -> {
                return checkValidateDay(31, day.toInt())
            }

            2 -> {
                if (year.isEmpty()) return checkValidateDay(28, day.toInt())
                return if (checkLeapYear(year.toInt())) checkValidateDay(29, day.toInt())
                else {
                    checkValidateDay(28, day.toInt())
                }
            }

            4, 6, 9, 11 -> {
                return checkValidateDay(30, day.toInt())
            }

            else -> {
                return ValidateResult.WRONGRANGE
            }
        }
    }

    private fun checkValidateDay(maxDay: Int, day: Int): ValidateResult {
        return if (day > maxDay || day == 0) {
            ValidateResult.WRONGRANGE
        } else {
            ValidateResult.SUCCESS
        }
    }

    private fun checkLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}