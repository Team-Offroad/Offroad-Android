package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.model.UserCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponsRepository
import com.teamoffroad.feature.mypage.presentation.component.getErrorMessage
import com.teamoffroad.feature.mypage.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GainedCouponViewModel @Inject constructor(
    private val userCouponsRepository: UserCouponsRepository
) : ViewModel() {

    private val _getUserCouponsState =
        MutableStateFlow<UiState<UserCoupons>>(UiState.Loading)
    val getUserCouponsState = _getUserCouponsState.asStateFlow()

    private val _userAvailableCoupons =
        MutableStateFlow<List<UserCoupons.AvailableCoupon>>(emptyList())
    val userAvailableCoupons = _userAvailableCoupons.asStateFlow()

    private val _userUsedCoupons =
        MutableStateFlow<List<UserCoupons.UsedCoupon>>(emptyList())
    val userUsedCoupons = _userUsedCoupons.asStateFlow()

    fun getUserCoupons() {
        viewModelScope.launch {
            runCatching {
                userCouponsRepository.getUserCoupons()
            }.onSuccess { state ->
                _getUserCouponsState.emit(UiState.Success(state))
                updateAvailableCoupons(state.availableCoupons)
                updateUsedCoupons(state.usedCoupons)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _getUserCouponsState.emit(UiState.Failure(errorMessage))
            }
        }
    }

    private fun updateAvailableCoupons(coupons: List<UserCoupons.AvailableCoupon>) {
        _userAvailableCoupons.value = coupons
    }

    private fun updateUsedCoupons(coupons: List<UserCoupons.UsedCoupon>) {
        _userUsedCoupons.value = coupons
    }
}