package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.model.UserCoupons
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
    private val userCouponRepository: UserCouponRepository,
) : ViewModel() {

    private val _getUserCouponListState =
        MutableStateFlow<UiState<UserCoupons>>(UiState.Loading)
    val getUserCouponListState = _getUserCouponListState.asStateFlow()

    private val _availableCouponsCount = MutableStateFlow<Int>(0)
    val availableCouponsCount = _availableCouponsCount.asStateFlow()

    private val _usedCouponsCount = MutableStateFlow<Int>(0)
    val usedCouponsCount = _usedCouponsCount.asStateFlow()

    private val _userAvailableCoupons = MutableStateFlow<List<UserCoupons.Coupons>>(emptyList())
    val userAvailableCoupons = _userAvailableCoupons.asStateFlow()

    private val _userUsedCoupons = MutableStateFlow<List<UserCoupons.Coupons>>(emptyList())
    val userUsedCoupons = _userUsedCoupons.asStateFlow()

    private var isLoadable = true

    fun getUserCoupons(isUsed: Boolean, cursorId: Int) {
        if (!isLoadable) return
        _getUserCouponListState.value = when (cursorId) {
            START_CURSOR_ID -> UiState.Loading
            else -> UiState.AdditionalLoading
        }

        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserCoupons(isUsed, COUPON_SIZE, cursorId)
            }.onSuccess { coupons ->
                if (coupons.coupons.isEmpty() && cursorId != START_CURSOR_ID) {
                    isLoadable = false
                }
                _getUserCouponListState.emit(UiState.Success(coupons))
                _availableCouponsCount.emit(coupons.availableCouponsCount)
                _usedCouponsCount.emit(coupons.usedCouponsCount)
                applyCoupons(isUsed, coupons.coupons)
            }.onFailure { throwable ->
                _getUserCouponListState.emit(UiState.Failure(getErrorMessage(throwable)))
            }
        }
    }

    private fun applyCoupons(isUsed: Boolean, coupons: List<UserCoupons.Coupons>) {
        if (isUsed) {
            _userUsedCoupons.value += coupons
        } else {
            _userAvailableCoupons.value += coupons
        }
    }


    companion object {
        const val COUPON_SIZE: Int = 8
        const val START_CURSOR_ID: Int = 0
    }
}