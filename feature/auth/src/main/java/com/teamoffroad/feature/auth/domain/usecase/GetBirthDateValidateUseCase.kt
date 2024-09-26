package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.presentation.component.BirthDateValidateResult
import java.time.LocalDate

class GetBirthDateValidateUseCase {
    suspend operator fun invoke(
        birthDate: String,
        year: String = "",
        month: String = "",
        day: String = "",
    ): BirthDateValidateResult {
        when (birthDate) {
            "year" -> {
                return when (year.toIntOrNull()?.let { checkInValidYear(it) }) {
                    true -> BirthDateValidateResult.Success
                    false -> BirthDateValidateResult.YearError
                    null -> BirthDateValidateResult.Error
                }
            }

            "month" -> {
                return when (month.toIntOrNull()?.let {
                    checkInValidMonth(it)
                }) {
                    true -> BirthDateValidateResult.Success
                    false -> BirthDateValidateResult.MonthError
                    null -> BirthDateValidateResult.Error
                }
            }

            "day" -> {
                return when (year.toIntOrNull()
                    ?.let { checkYear ->
                        month.toIntOrNull()
                            ?.let { checkMonth ->
                                day.toIntOrNull()
                                    ?.let { checkDay ->
                                        checkInValidDay(
                                            checkYear,
                                            checkMonth,
                                            checkDay
                                        )
                                    }
                            }
                    }) {
                    true -> BirthDateValidateResult.Success
                    false -> BirthDateValidateResult.DayError
                    null -> BirthDateValidateResult.Error
                }
            }
            else -> return BirthDateValidateResult.Error
        }
    }

    private fun checkInValidYear(year: Int): Boolean {
        return year in 1900..LocalDate.now().year
    }

    private fun checkInValidMonth(month: Int): Boolean {
        return month in 1..12
    }

    private fun checkInValidDay(year: Int, month: Int, day: Int): Boolean {
        val daysInMonth = when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (checkLeapYear(year)) 29 else 28
            else -> 0
        }
        return day in 1..daysInMonth
    }

    private fun checkLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}