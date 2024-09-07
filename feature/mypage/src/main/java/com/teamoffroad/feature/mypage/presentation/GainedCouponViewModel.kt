package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.model.UserCouponList
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository
import com.teamoffroad.feature.mypage.presentation.component.getErrorMessage
import com.teamoffroad.feature.mypage.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GainedCouponViewModel @Inject constructor(
    private val userCouponRepository: UserCouponRepository
) : ViewModel() {

    private val _getUserCouponListState =
        MutableStateFlow<UiState<UserCouponList>>(UiState.Loading)
    val getUserCouponsState = _getUserCouponListState.asStateFlow()

    private val _userAvailableCoupons =
        MutableStateFlow<List<UserCouponList.AvailableCoupon>>(emptyList())
    val userAvailableCoupons = _userAvailableCoupons.asStateFlow()

    private val _userUsedCoupons =
        MutableStateFlow<List<UserCouponList.UsedCoupon>>(emptyList())
    val userUsedCoupons = _userUsedCoupons.asStateFlow()

    fun getUserCoupons() {
        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserCoupons()
            }.onSuccess { state ->
                _getUserCouponListState.emit(UiState.Success(state))
                updateAvailableCoupons(state.availableCoupons)
                updateUsedCoupons(state.usedCoupons)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _getUserCouponListState.emit(UiState.Failure(errorMessage))
            }
        }
    }


    private fun updateAvailableCoupons(coupons: List<UserCouponList.AvailableCoupon>) {
        _userAvailableCoupons.value = coupons
    }

    private fun updateUsedCoupons(coupons: List<UserCouponList.UsedCoupon>) {
        _userUsedCoupons.value = coupons
    }
}