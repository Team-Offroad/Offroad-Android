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
    private val _getUserAvailableCouponListState =
        MutableStateFlow<UiState<List<UserCoupons>>>(UiState.Loading)
    val getUserAvailableCouponListState = _getUserAvailableCouponListState.asStateFlow()

    private val _getUserUsedCouponListState =
        MutableStateFlow<UiState<List<UserCoupons>>>(UiState.Loading)
    val getUSerUsedCouponListState = _getUserUsedCouponListState.asStateFlow()

    private val _userAvailableCoupons =
        MutableStateFlow<List<UserCoupons>>(emptyList())
    val userAvailableCoupons = _userAvailableCoupons.asStateFlow()

    private val _userUsedCoupons =
        MutableStateFlow<List<UserCoupons>>(emptyList())
    val userUsedCoupons = _userUsedCoupons.asStateFlow()

    fun getUserAvailableCoupons() {
        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserCoupons(false, 4, 0)
            }.onSuccess { state ->
                _getUserAvailableCouponListState.emit(UiState.Success(state))
                updateAvailableCoupons(state)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _getUserAvailableCouponListState.emit(UiState.Failure(errorMessage))
            }
        }
    }

    fun getUserUsedCoupons() {
        viewModelScope.launch {
            runCatching {
                userCouponRepository.fetchUserCoupons(true, 4, 0)
            }.onSuccess { state ->
                _getUserUsedCouponListState.emit(UiState.Success(state))
                updateUsedCoupons(state)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _getUserUsedCouponListState.emit(UiState.Failure(errorMessage))
            }
        }
    }


    private fun updateAvailableCoupons(coupons: List<UserCoupons>) {
        _userAvailableCoupons.value = coupons
    }

    private fun updateUsedCoupons(coupons: List<UserCoupons>) {
        _userUsedCoupons.value = coupons
    }
}