package com.teamoffroad.feature.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.model.ValidateResult
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.feature.auth.domain.usecase.DayValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetNicknameValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.MonthValidateUseCase
import com.teamoffroad.feature.auth.domain.usecase.YearValidateUseCase
import com.teamoffroad.feature.auth.presentation.model.BirthDateFocus
import com.teamoffroad.feature.auth.presentation.model.DateValidateResult
import com.teamoffroad.feature.auth.presentation.model.NicknameValidateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapLatest
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

    private val _birthDateTextFieldFocus: MutableStateFlow<BirthDateFocus> =
        MutableStateFlow(BirthDateFocus.NONE)
    private val birthDateTextFieldFocus: StateFlow<BirthDateFocus> =
        _birthDateTextFieldFocus.asStateFlow()

    private val _signUpSideEffect: Channel<SignUpSideEffect> = Channel()
    val signUpSideEffect = _signUpSideEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            signUpUiState.mapLatest {
                _signUpUiState.emit(checkDateValidate())
            }.collectLatest {
                if (signUpUiState.value.year.length == 4 && signUpUiState.value.yearValidateResult == DateValidateResult.Success && birthDateTextFieldFocus.value == BirthDateFocus.YEAR) {
                    _birthDateTextFieldFocus.emit(BirthDateFocus.MONTH)
                    _signUpSideEffect.send(SignUpSideEffect.FocusNext)
                }
                if (signUpUiState.value.month.length == 2 && signUpUiState.value.monthValidateResult == DateValidateResult.Success && birthDateTextFieldFocus.value == BirthDateFocus.MONTH) {
                    _birthDateTextFieldFocus.emit(BirthDateFocus.DAY)
                    _signUpSideEffect.send(SignUpSideEffect.FocusNext)
                }
                if (signUpUiState.value.day.length == 2 && signUpUiState.value.dayValidateResult == DateValidateResult.Success && birthDateTextFieldFocus.value == BirthDateFocus.DAY) {
                    _birthDateTextFieldFocus.emit(BirthDateFocus.NONE)
                    _signUpSideEffect.send(SignUpSideEffect.FocusClear)
                }
            }
        }
    }

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

    fun updateBirthDateFocus(focus: BirthDateFocus) {
        viewModelScope.launch {
            _birthDateTextFieldFocus.emit(focus)
        }
    }

    private fun checkDateValidate(date: String): SignUpState {
        val inputDate = date.split(",")
        var newSignupState = _signUpUiState.value
        when (yearValidateUseCase(inputDate[0])) {
            ValidateResult.WRONGRANGE -> {
                newSignupState = newSignupState.copy(
                    yearValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                newSignupState = newSignupState.copy(
                    yearValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                newSignupState = newSignupState.copy(
                    yearValidateResult = DateValidateResult.Success
                )
            }
        }
        when (monthValidateUseCase(inputDate[1])) {
            ValidateResult.WRONGRANGE -> {
                newSignupState = newSignupState.copy(
                    monthValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                newSignupState = newSignupState.copy(
                    monthValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                newSignupState = newSignupState.copy(
                    monthValidateResult = DateValidateResult.Success
                )
            }
        }
        when (dayValidateUseCase(inputDate[0], inputDate[1], inputDate[2])) {
            ValidateResult.WRONGRANGE -> {
                newSignupState = newSignupState.copy(
                    dayValidateResult = DateValidateResult.Error
                )
            }

            ValidateResult.EMPTY -> {
                newSignupState = newSignupState.copy(
                    dayValidateResult = DateValidateResult.Empty
                )
            }

            ValidateResult.SUCCESS -> {
                newSignupState = newSignupState.copy(
                    dayValidateResult = DateValidateResult.Success
                )
            }
        }
        return newSignupState
    }

    private fun checkDateValidate(): SignUpState {
        var newState = _signUpUiState.value
        if (newState.year.isBlank() && newState.month.isBlank() && newState.day.isBlank()) {
            newState = newState.copy(
                date = null
            )
            return newState
        } else {
            newState = newState.copy(
                date = newState.year + "," + newState.month + "," + newState.day
            )
            return checkDateValidate(newState.date ?: "")
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
            _signUpSideEffect.send(SignUpSideEffect.NavigateSetCharacter)
        }
    }
}