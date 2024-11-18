package com.teamoffroad.feature.mypage.presentation.model

import com.teamoffroad.feature.mypage.domain.model.GainedEmblem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class GainedEmblemsUiState(
    val emblemList: ImmutableList<GainedEmblem> = emptyList<GainedEmblem>().toImmutableList(),
    val gainedEmblemsValidateResult: GainedEmblemsResult = GainedEmblemsResult.Empty,
)
