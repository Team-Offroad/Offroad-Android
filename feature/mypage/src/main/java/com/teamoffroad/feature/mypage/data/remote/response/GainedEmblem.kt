package com.teamoffroad.feature.mypage.data.remote.response

import com.teamoffroad.feature.mypage.domain.model.GainedEmblems
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GainedEmblem(
    @SerialName("clearConditionQuestName")
    val clearConditionQuestName: String,

    @SerialName("emblemName")
    val emblemName: String,

    @SerialName("isNewGained")
    val isNewGained: Boolean
)

fun GainedEmblem.toEmblemsList() =
    run {
        GainedEmblems(
            emblemTitle = emblemName,
            emblemSubtitle = clearConditionQuestName,
            isNew = isNewGained,
            isLock = false
        )

    }
