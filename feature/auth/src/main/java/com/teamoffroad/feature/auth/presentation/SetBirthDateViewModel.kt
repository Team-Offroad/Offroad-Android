package com.teamoffroad.feature.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SetBirthDateViewModel @Inject constructor(
) : ViewModel() {
    private val _isCheckedYear: MutableStateFlow<Int> = MutableStateFlow(0)
    val isCheckedYear: StateFlow<Int> = _isCheckedYear.asStateFlow()

    private val _isCheckedMonth: MutableStateFlow<Int> = MutableStateFlow(0)
    val isCheckedMonth: StateFlow<Int> = _isCheckedMonth.asStateFlow()

    private val _isCheckedDay: MutableStateFlow<Int> = MutableStateFlow(0)
    val isCheckedDay: StateFlow<Int> = _isCheckedDay.asStateFlow()

    fun updateCheckedYear(year: String) {
        _isCheckedYear.value = year.toInt()
        Log.d("asdasd", year)
    }

    fun updateCheckedMonth(month: String) {
        _isCheckedMonth.value = month.toInt()
        Log.d("asdasd", month.toString())
    }

    fun updateCheckedDate(day: String) {
        _isCheckedDay.value = day.toInt()
        Log.d("asdasd", day.toString())
    }
}