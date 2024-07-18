package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SetBirthDateViewModel @Inject constructor(
) : ViewModel() {
    private val _isCheckedYear: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedYear: StateFlow<String> = _isCheckedYear.asStateFlow()

    private val _isCheckedMonth: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedMonth: StateFlow<String> = _isCheckedMonth.asStateFlow()

    private val _isCheckedDay: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedDay: StateFlow<String> = _isCheckedDay.asStateFlow()

    fun updateCheckedYear(year: String) {
        _isCheckedYear.value = year
    }

    fun updateCheckedMonth(month: String) {
        _isCheckedMonth.value = month
    }

    fun updateCheckedDate(day: String) {
        _isCheckedDay.value = day
    }
}