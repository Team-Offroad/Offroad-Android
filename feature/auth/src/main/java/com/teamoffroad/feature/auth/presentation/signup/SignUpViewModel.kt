package com.teamoffroad.feature.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.model.ValidateResult
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.feature.auth.domain.usecase.DayValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetNicknameValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.MonthValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.YearValidateUseCase
import com.teamoffroad.feature.auth.presentation.model.DateValidateResult
import com.teamoffroad.feature.auth.presentation.model.NicknameValidateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val getNicknameValidateUseCase: GetNicknameValidateUseCase,
    private val yearValidateUseCase: YearValidateUseCase,
    private val monthValidateUseCase: MonthValidateUseCase,
    private val dayValidateUseCase: DayValidateUseCase,
) : ViewModel() {
    private val _signUpUiState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val signUpUiState: StateFlow<SignUpState> = _signUpUiState.asStateFlow()

    private val _signUpSideEffect: Channel<SignUpSideEffect> = Channel()
    val signUpSideEffect = _signUpSideEffect.receiveAsFlow()

    fun updateNicknamesValid(nickname: String) {
        _signUpUiState.value = signUpUiState.value.copy(
            nickname = nickname,
            nicknameScreenResult = getNicknameValidateUseCase.invoke(nickname)
        )
    }

    fun getDuplicateNickname() {
        viewModelScope.launch {
            runCatching { authRepository.fetchDuplicateNickname(_signUpUiState.value.nickname) }
                .onSuccess {
                    when (it) {
                        true -> {
                            _signUpUiState.value = signUpUiState.value.copy(
                                nicknameScreenResult = NicknameValidateResult.Duplicate
                            )
                        }

                        false -> {
                            _signUpUiState.value = signUpUiState.value.copy(
                                nicknameScreenResult = NicknameValidateResult.Success
                            )
                        }
                    }
                }
                .onFailure {
                    _signUpUiState.value = signUpUiState.value.copy(
                        nicknameScreenResult = NicknameValidateResult.Error
                    )
                }
        }
    }

    fun updateYear(year: String) {
        _signUpUiState.value = signUpUiState.value.copy(
            year = year
        )
    }

    fun updateMonth(month: String) {
        _signUpUiState.value = signUpUiState.value.copy(
            month = month
        )
    }

    fun updateDay(day: String) {
        _signUpUiState.value = signUpUiState.value.copy(
            day = day
        )
    }

    fun updateMonthLength() {
        if (_signUpUiState.value.month.length == 1) {
            _signUpUiState.value =
                signUpUiState.value.copy(month = "0${signUpUiState.value.month}")
        }
    }

    fun updateDayLength() {
        if (_signUpUiState.value.day.length == 1) {
            _signUpUiState.value =
                signUpUiState.value.copy(day = "0${signUpUiState.value.day}")
        }
    }

    private fun checkDateValidate(date: String) {
        val inputDate = date.split(",")
        when (yearValidateUseCase(inputDate[0])) {
            ValidateResult.WRONGRANGE -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    yearValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    yearValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    yearValidateResult = DateValidateResult.Success
                )
            }
        }
        when (monthValidateUseCase(inputDate[1])) {
            ValidateResult.WRONGRANGE -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    monthValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    monthValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    monthValidateResult = DateValidateResult.Success
                )
            }
        }
        when (dayValidateUseCase(inputDate[0], inputDate[1], inputDate[2])) {
            ValidateResult.WRONGRANGE -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    dayValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    dayValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                _signUpUiState.value = signUpUiState.value.copy(
                    dayValidateResult = DateValidateResult.Success
                )
            }
        }
    }

    fun checkDateValidate() {
        viewModelScope.launch {
            _signUpUiState.value = signUpUiState.value.copy(
                date = signUpUiState.value.year + "," + signUpUiState.value.month + "," + signUpUiState.value.day
            )
            checkDateValidate(signUpUiState.value.date)
        }
    }

    fun updateCheckedGender(gender: String) {
        if (gender == signUpUiState.value.selectedGender) {
            _signUpUiState.value = signUpUiState.value.copy(
                selectedGender = null,
                genderScreenResult = false

            )
        } else
            _signUpUiState.value = signUpUiState.value.copy(
                selectedGender = gender,
                genderScreenResult = true
            )
    }

    fun initBirthDate() {
        _signUpUiState.value = signUpUiState.value.copy(
            year = "",
            month = "",
            day = "",
            date = "",
            yearValidateResult = DateValidateResult.Empty,
            monthValidateResult = DateValidateResult.Empty,
            dayValidateResult = DateValidateResult.Empty
        )
    }

    fun initGender() {
        _signUpUiState.value = signUpUiState.value.copy(
            selectedGender = null,
            genderScreenResult = false
        )
    }

    fun navigateSetCharacter() {
        viewModelScope.launch {
            _signUpSideEffect.send(SignUpSideEffect.Success)
        }
    }
}