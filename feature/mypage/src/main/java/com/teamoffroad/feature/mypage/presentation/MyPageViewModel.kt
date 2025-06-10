package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryCheckLatestUseCase
import com.teamoffroad.feature.mypage.domain.model.MyPageUser
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
import com.teamoffroad.feature.mypage.presentation.component.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val homeUserRepository: com.teamoffroad.feature.home.domain.repository.UserRepository,
    private val getDiaryCheckLatestUseCase: GetDiaryCheckLatestUseCase,
) : ViewModel() {
    private val _myPageUser = MutableStateFlow<MyPageUser>(MyPageUser("", "", 0, 0, 0, ""))
    val myPageUser = _myPageUser.asStateFlow()

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _newDiaryExist = MutableStateFlow(false)
    val newDiaryExist = _newDiaryExist.asStateFlow()

    private val _characterName = MutableStateFlow("")
    val characterName = _characterName.asStateFlow()


    fun getCharacterName() {
        viewModelScope.launch {
            runCatching {
                homeUserRepository.getUsersAdventuresInformation("NONE")
            }.onSuccess {
                _characterName.value = it.characterName
            }
        }
    }

    fun getDiaryCheckLatest() {
        viewModelScope.launch {
            getDiaryCheckLatestUseCase.invoke().onSuccess {
                if (it != null) {
                    _newDiaryExist.emit(it)
                }
            }
        }
    }

    fun getMyPageUser() {
        viewModelScope.launch {
            runCatching {
                userRepository.fetchMyPage()
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
