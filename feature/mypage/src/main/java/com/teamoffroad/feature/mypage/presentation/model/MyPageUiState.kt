package com.teamoffroad.feature.mypage.presentation.model

data class MyPageUiState(
    val userData: FakeUserModel = FakeUserModel.dummyUser,
    val loading: Boolean = true,
    val error: Boolean = false
)