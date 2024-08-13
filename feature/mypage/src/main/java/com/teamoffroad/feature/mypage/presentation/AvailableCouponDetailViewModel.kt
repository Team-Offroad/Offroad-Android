package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AvailableCouponDetailViewModel @Inject constructor(

) : ViewModel() {
    private val _couponCode: MutableStateFlow<String> = MutableStateFlow("")
    val couponCode = _couponCode.asStateFlow()

    fun updateCode(code: String) {
        viewModelScope.launch {
            _couponCode.value = code
        }
    }
}