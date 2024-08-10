package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import com.teamoffroad.feature.mypage.presentation.model.FakeUserModel
import com.teamoffroad.feature.mypage.presentation.model.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState: MutableStateFlow<MyPageUiState> = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = MyPageUiState(
            userData = FakeUserModel.dummyUser,
            loading = false,
            error = false
        )
    }
}