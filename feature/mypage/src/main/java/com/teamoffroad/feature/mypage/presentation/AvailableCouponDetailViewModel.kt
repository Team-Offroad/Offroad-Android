package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository
import com.teamoffroad.feature.mypage.presentation.component.getErrorMessage
import com.teamoffroad.feature.mypage.presentation.model.CheckCouponState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AvailableCouponDetailViewModel @Inject constructor(
    private val userCouponRepository: UserCouponRepository,
) : ViewModel() {
    private val _couponCode: MutableStateFlow<String> = MutableStateFlow("")
    val couponCode = _couponCode.asStateFlow()

    private val _couponCodeSuccess: MutableStateFlow<CheckCouponState> =
        MutableStateFlow(CheckCouponState.NONE)
    val couponCodeSuccess = _couponCodeSuccess.asStateFlow()

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage = _errorMessage.asStateFlow()

    fun updateCode(code: String) {
        viewModelScope.launch {
            _couponCode.value = code
        }
    }

    fun updateCoupon(coupon: UseCoupon) {
        viewModelScope.launch {
            runCatching {
                userCouponRepository.saveUseCoupon(coupon)
            }.onSuccess { state ->
                if (state) updateCouponCodeSuccess(CheckCouponState.SUCCESS)
                else updateCouponCodeSuccess(CheckCouponState.FAIL)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                updateErrorMessage(errorMessage)
            }
        }
    }

    fun updateCouponCodeSuccess(fakeCheckCouponCode: CheckCouponState) {
        viewModelScope.launch {
            _couponCodeSuccess.value = fakeCheckCouponCode
        }
    }

    fun updateErrorMessage(errorMessage: String) {
        _errorMessage.value = errorMessage
    }
}