package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.presentation.model.CheckCouponState
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

    private val _couponCodeSuccess: MutableStateFlow<CheckCouponState> =
        MutableStateFlow(CheckCouponState.NONE)
    val couponCodeSuccess = _couponCodeSuccess.asStateFlow()

    fun updateCode(code: String) {
        viewModelScope.launch {
            _couponCode.value = code
        }
    }

    fun updateCouponCodeSuccess(fakeCheckCouponCode: CheckCouponState) {
        viewModelScope.launch {
            _couponCodeSuccess.value = fakeCheckCouponCode
        }
    }
}