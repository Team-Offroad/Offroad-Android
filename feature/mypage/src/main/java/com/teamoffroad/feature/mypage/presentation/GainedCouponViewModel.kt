package com.teamoffroad.feature.mypage.presentation

import android.util.Log
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
        MutableStateFlow<UiState<List<UserCoupons>>>(UiState.Loading)
    val getUserCouponListState = _getUserCouponListState.asStateFlow()

    private val _userAvailableCoupons = MutableStateFlow<List<UserCoupons>>(emptyList())
    val userAvailableCoupons = _userAvailableCoupons.asStateFlow()

    private val _userUsedCoupons = MutableStateFlow<List<UserCoupons>>(emptyList())
    val userUsedCoupons = _userUsedCoupons.asStateFlow()

    fun getUserCoupons(isUsed: Boolean, cursorId: Int) {
        Log.d("lastItemCursorId", cursorId.toString())
        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserCoupons(isUsed, COUPON_SIZE, cursorId)
            }.onSuccess { coupons ->
                _getUserCouponListState.emit(UiState.Success(coupons))
                applyCoupons(isUsed, coupons)
            }.onFailure { throwable ->
                _getUserCouponListState.emit(UiState.Failure(getErrorMessage(throwable)))
            }
        }
    }

    private fun applyCoupons(isUsed: Boolean, coupons: List<UserCoupons>) {
        if (isUsed) {
            _userUsedCoupons.value += coupons
        } else {
            _userAvailableCoupons.value += coupons
        }
    }

    companion object {
        const val COUPON_SIZE: Int = 4
        const val START_CURSOR_ID: Int = 0
    }
}