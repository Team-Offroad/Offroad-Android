package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.model.UserAvailableCoupons
import com.teamoffroad.feature.mypage.domain.model.UserUsedCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository
import com.teamoffroad.feature.mypage.presentation.model.AvailableCouponListUiState
import com.teamoffroad.feature.mypage.presentation.model.UsedCouponListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GainedCouponViewModel @Inject constructor(
    private val userCouponRepository: UserCouponRepository,
) : ViewModel() {

    private val _availableCouponListState =
        MutableStateFlow(AvailableCouponListUiState())
    val availableCouponListState = _availableCouponListState.asStateFlow()

    private val _usedCouponListState =
        MutableStateFlow(UsedCouponListUiState())
    val usedCouponListState = _usedCouponListState.asStateFlow()

    private val _availableCouponsCount = MutableStateFlow<Int>(0)
    val availableCouponsCount = _availableCouponsCount.asStateFlow()

    private val _usedCouponsCount = MutableStateFlow<Int>(0)
    val usedCouponsCount = _usedCouponsCount.asStateFlow()

    private val _userAvailableCoupons =
        MutableStateFlow<List<UserAvailableCoupons.AvailableCoupons>>(emptyList())
    val userAvailableCoupons = _userAvailableCoupons.asStateFlow()

    private val _userUsedCoupons = MutableStateFlow<List<UserUsedCoupons.UsedCoupons>>(emptyList())
    val userUsedCoupons = _userUsedCoupons.asStateFlow()

    fun getUserAvailableCoupons(isUsed: Boolean, cursorId: Int) {
        _availableCouponListState.value = _availableCouponListState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserAvailableCoupons(isUsed, COUPON_SIZE, cursorId)
            }.onSuccess { coupons ->
                _availableCouponsCount.emit(coupons.availableCouponsCount)
                applyAvailableCoupons(coupons.coupons)
                _availableCouponListState.value = _availableCouponListState.value.copy(
                    isLoading = false
                )
            }.onFailure {
                _availableCouponListState.value = _availableCouponListState.value.copy(
                    isLoading = false
                )
            }
        }
    }

    fun getUserUsedCoupons(isUsed: Boolean, cursorId: Int) {
        _usedCouponListState.value = _usedCouponListState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserUsedCoupons(isUsed, COUPON_SIZE, cursorId)
            }.onSuccess { coupons ->
                _usedCouponsCount.emit(coupons.usedCouponsCount)
                applyUsedCoupons(coupons.coupons)
                _usedCouponListState.value = _usedCouponListState.value.copy(
                    isLoading = false
                )
            }.onFailure {
                _usedCouponListState.value = _usedCouponListState.value.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun applyAvailableCoupons(coupons: List<UserAvailableCoupons.AvailableCoupons>) {
        _userAvailableCoupons.value += coupons
    }

    private fun applyUsedCoupons(coupons: List<UserUsedCoupons.UsedCoupons>) {
        _userUsedCoupons.value += coupons

    }

    fun initCoupons() {
        _userUsedCoupons.value = emptyList()
        _userAvailableCoupons.value = emptyList()
    }


    companion object {
        const val COUPON_SIZE: Int = 8
        const val START_CURSOR_ID: Int = 0
    }
}