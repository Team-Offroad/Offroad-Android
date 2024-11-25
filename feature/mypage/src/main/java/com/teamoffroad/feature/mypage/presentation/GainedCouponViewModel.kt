package com.teamoffroad.feature.mypage.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.model.UserAvailableCoupons
import com.teamoffroad.feature.mypage.domain.model.UserUsedCoupons
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

    private val _getUserAvailableCouponListState =
        MutableStateFlow<UiState<UserAvailableCoupons>>(UiState.Loading)
    val getUserCouponListState = _getUserAvailableCouponListState.asStateFlow()

    private val _getUserUsedCouponListState =
        MutableStateFlow<UiState<UserUsedCoupons>>(UiState.Loading)
    val getUserUsedCouponListState = _getUserUsedCouponListState.asStateFlow()

    private val _availableCouponsCount = MutableStateFlow<Int>(0)
    val availableCouponsCount = _availableCouponsCount.asStateFlow()

    private val _usedCouponsCount = MutableStateFlow<Int>(0)
    val usedCouponsCount = _usedCouponsCount.asStateFlow()

    private val _userAvailableCoupons = MutableStateFlow<List<UserAvailableCoupons.AvailableCoupons>>(emptyList())
    val userAvailableCoupons = _userAvailableCoupons.asStateFlow()

    private val _userUsedCoupons = MutableStateFlow<List<UserUsedCoupons.UsedCoupons>>(emptyList())
    val userUsedCoupons = _userUsedCoupons.asStateFlow()

    fun getUserAvailableCoupons(isUsed: Boolean, cursorId: Int) {
        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserAvailableCoupons(isUsed, COUPON_SIZE, cursorId)
            }.onSuccess { coupons ->
                _getUserAvailableCouponListState.emit(UiState.Success(coupons))
                _availableCouponsCount.emit(coupons.availableCouponsCount)
                Log.d("viewmodel availableCoupons", coupons.coupons.toString())
                applyAvailableCoupons(isUsed, coupons.coupons)
            }.onFailure { throwable ->
                _getUserAvailableCouponListState.emit(UiState.Failure(getErrorMessage(throwable)))
            }
        }
    }

    fun getUserUsedCoupons(isUsed: Boolean, cursorId: Int) {
        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserUsedCoupons(isUsed, COUPON_SIZE, cursorId)
            }.onSuccess { coupons ->
                _getUserUsedCouponListState.emit(UiState.Success(coupons))
                _usedCouponsCount.emit(coupons.usedCouponsCount)
                Log.d("viewmodel usedCoupons", coupons.coupons.toString())
                applyUsedCoupons(isUsed, coupons.coupons)
            }.onFailure { throwable ->
                _getUserAvailableCouponListState.emit(UiState.Failure(getErrorMessage(throwable)))
            }
        }
    }

    private fun applyAvailableCoupons(isUsed: Boolean, coupons: List<UserAvailableCoupons.AvailableCoupons>) {
        _userAvailableCoupons.value += coupons
    }

    private fun applyUsedCoupons(isUsed: Boolean, coupons: List<UserUsedCoupons.UsedCoupons>) {
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