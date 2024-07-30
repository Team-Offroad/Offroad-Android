package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.usecase.GetBirthDateValidateUseCase
import com.teamoffroad.feature.auth.presentation.component.BirthDateValidateResult
import com.teamoffroad.feature.auth.presentation.model.SetBirthDateUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetBirthDateViewModel @Inject constructor(
    private val getBirthDateValidateUseCase: GetBirthDateValidateUseCase
) : ViewModel() {
    private val _birthDateUiState: MutableStateFlow<SetBirthDateUiState> = MutableStateFlow(
        SetBirthDateUiState()
    )
    val birthDateUiState: StateFlow<SetBirthDateUiState> = _birthDateUiState.asStateFlow()

    fun updateCheckedYear(year: String) {
        viewModelScope.launch {
            if (year.isBlank()) {
                _birthDateUiState.value =
                    _birthDateUiState.value.copy(
                        year = "",
                        birthDateValidateResult = BirthDateValidateResult.Empty
                    )
            } else {
                _birthDateUiState.value =
                    _birthDateUiState.value.copy(
                        year = year,
                        birthDateValidateResult = getBirthDateValidateUseCase.invoke(
                            "year",
                            year = year
                        )
                    )
            }
        }
    }

    fun updateCheckedMonth(month: String) {
        viewModelScope.launch {
            if (month.isBlank()) {
                _birthDateUiState.value =
                    _birthDateUiState.value.copy(
                        month = "",
                    )
            } else {
                _birthDateUiState.value =
                    _birthDateUiState.value.copy(
                        month = month,
                        birthDateValidateResult = getBirthDateValidateUseCase.invoke(
                            "month",
                            month = month
                        )
                    )
            }
        }
    }

    fun updateCheckedDate(day: String) {
        viewModelScope.launch {
            if (day.isBlank()) {
                _birthDateUiState.value =
                    _birthDateUiState.value.copy(
                        day = "",
                    )
            } else {
                _birthDateUiState.value =
                    _birthDateUiState.value.copy(
                        day = day,
                        birthDateValidateResult = getBirthDateValidateUseCase.invoke(
                            birthDate = "day",
                            year = _birthDateUiState.value.year,
                            month = _birthDateUiState.value.month,
                            day = day
                        )
                    )
            }
        }
    }
}