package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.model.MyPageUser
import com.teamoffroad.feature.mypage.domain.repository.MyPageUserRepository
import com.teamoffroad.feature.mypage.presentation.component.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageUserRepository: MyPageUserRepository
) : ViewModel() {
    private val _myPageUser = MutableStateFlow<MyPageUser>(MyPageUser("", "", 0, 0, 0))
    val myPageUser = _myPageUser.asStateFlow()

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage = _errorMessage.asStateFlow()

    fun getMyPageUser() {
        viewModelScope.launch {
            runCatching {
                myPageUserRepository.getUsersMyPage()
            }.onSuccess { state ->
                updateMyPageUser(state.copy())
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                updateErrorMessage(errorMessage)
            }
        }
    }

    private fun updateMyPageUser(myPageUser: MyPageUser) {
        _myPageUser.value = myPageUser
    }

    fun updateErrorMessage(errorMessage: String) {
        _errorMessage.value = errorMessage
    }
}