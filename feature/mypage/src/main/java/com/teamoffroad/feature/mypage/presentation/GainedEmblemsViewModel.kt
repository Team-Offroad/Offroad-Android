package com.teamoffroad.feature.mypage.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.model.GainedEmblems
import com.teamoffroad.feature.mypage.domain.usecase.UserEmblemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GainedEmblemsViewModel @Inject constructor(
    private val gainedEmblemUserCase: UserEmblemsUseCase
) : ViewModel() {

    private val _gainedEmblems = MutableStateFlow<List<GainedEmblems>?>(emptyList())
    val gainedEmblems: StateFlow<List<GainedEmblems>?> = _gainedEmblems.asStateFlow()

    fun getEmblems() {
        viewModelScope.launch {
            runCatching {
                gainedEmblemUserCase.invoke()
            }.onSuccess {
            }.onFailure {
            }
        }
    }

    val assd = mutableListOf(
        GainedEmblems("ASD", "asd", true, true),
        GainedEmblems("ASD", "asd", false, true),
        GainedEmblems("상수 고수 악수 박수", "asd", false, false),
        GainedEmblems("ASD", "asd", true, false),
        GainedEmblems("ASD", "asd", true, true),
        GainedEmblems("ASD", "asd", true, true),
        GainedEmblems("ASD", "asd", true, true),
        GainedEmblems("ASD", "asd", true, true)
    )
}