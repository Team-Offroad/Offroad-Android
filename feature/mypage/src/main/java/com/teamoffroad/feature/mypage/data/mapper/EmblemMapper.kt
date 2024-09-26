package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.remote.response.GainedEmblemResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.NotGainedEmblemResponseDto
import com.teamoffroad.feature.mypage.domain.model.GainedEmblem

fun GainedEmblemResponseDto.toGainedEmblemsList() =
    GainedEmblem(
        emblemTitle = emblemName,
        emblemSubtitle = clearConditionQuestName,
        isNew = isNewGained,
        isLock = false
    )

fun NotGainedEmblemResponseDto.toNotGainedEmblemsList() =
    GainedEmblem(
        emblemTitle = emblemName,
        emblemSubtitle = clearConditionQuestName,
        isNew = isNewGained,
        isLock = true
    )