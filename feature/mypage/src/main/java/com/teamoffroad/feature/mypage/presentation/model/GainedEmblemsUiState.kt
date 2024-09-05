package com.teamoffroad.feature.mypage.presentation.model

import com.teamoffroad.feature.mypage.domain.model.GainedEmblems
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class GainedEmblemsUiState(
    val emblemList: ImmutableList<GainedEmblems> = emptyList<GainedEmblems>().toImmutableList(),
    val nicknameValidateResult: GainedEmblemsResult = GainedEmblemsResult.Empty,
)
