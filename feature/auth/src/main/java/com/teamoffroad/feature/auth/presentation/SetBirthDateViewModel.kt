package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.model.ValidateResult
import com.teamoffroad.feature.auth.domain.usecase.DayValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.MonthValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.YearValidateUseCase
import com.teamoffroad.feature.auth.presentation.component.DateValidateResult
import com.teamoffroad.feature.auth.presentation.model.SetBirthDateUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetBirthDateViewModel @Inject constructor(
    private val yearValidateUseCase: YearValidateUseCase,
    private val monthValidateUseCase: MonthValidateUseCase,
    private val dayValidateUseCase: DayValidateUseCase,
) : ViewModel() {
    private val _birthDateUiState: MutableStateFlow<SetBirthDateUiState> = MutableStateFlow(
        SetBirthDateUiState()
    )
    val birthDateUiState: StateFlow<SetBirthDateUiState> = _birthDateUiState.asStateFlow()

    init {
        viewModelScope.launch {
            birthDateUiState.collectLatest {
                _birthDateUiState.value = birthDateUiState.value.copy(
                    date = birthDateUiState.value.year + "," + birthDateUiState.value.month + "," + birthDateUiState.value.day
                )
                checkDateValidate(birthDateUiState.value.date)
            }
        }
    }

    fun updateYearState(year: String) {
        viewModelScope.launch {
            _birthDateUiState.value = _birthDateUiState.value.copy(
                year = year
            )
        }
    }

    fun updateMonthState(month: String) {
        viewModelScope.launch {
            _birthDateUiState.value = _birthDateUiState.value.copy(
                month = month
            )
        }
    }

    fun updateDateState(day: String) {
        viewModelScope.launch {
            _birthDateUiState.value = _birthDateUiState.value.copy(
                day = day
            )
        }
    }

    fun updateMonthLength() {
        if (_birthDateUiState.value.month.length == 1) {
            _birthDateUiState.value =
                _birthDateUiState.value.copy(month = "0${_birthDateUiState.value.month}")
        }
    }

    fun updateDayLength() {
        if (_birthDateUiState.value.day.length == 1) {
            _birthDateUiState.value =
                _birthDateUiState.value.copy(day = "0${_birthDateUiState.value.day}")
        }
    }

    private fun checkDateValidate(date: String) {
        val inputDate = date.split(",")
        when (yearValidateUseCase(inputDate[0])) {
            ValidateResult.WRONGRANGE -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    yearValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    yearValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    yearValidateResult = DateValidateResult.Success
                )
            }
        }
        when (monthValidateUseCase(inputDate[1])) {
            ValidateResult.WRONGRANGE -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    monthValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    monthValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    monthValidateResult = DateValidateResult.Success
                )
            }
        }
        when (dayValidateUseCase(inputDate[0], inputDate[1], inputDate[2])) {
            ValidateResult.WRONGRANGE -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    dayValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    dayValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                _birthDateUiState.value = _birthDateUiState.value.copy(
                    dayValidateResult = DateValidateResult.Success
                )
            }
        }
    }
}