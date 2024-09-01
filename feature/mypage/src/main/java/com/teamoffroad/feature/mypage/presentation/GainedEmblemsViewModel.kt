package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import com.teamoffroad.feature.mypage.presentation.model.GainedEmblem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GainedEmblemsViewModel @Inject constructor(
) : ViewModel() {

    val assd = listOf(GainedEmblem("ASD","asd", true, true),
        GainedEmblem("ASD","asd", false, true),
        GainedEmblem("상수 고수 악수 박수","asd", false, false),
        GainedEmblem("ASD","asd", true, false),
        GainedEmblem("ASD","asd", true, true),
        GainedEmblem("ASD","asd", true, true),
        GainedEmblem("ASD","asd", true, true),
        GainedEmblem("ASD","asd", true, true))
}