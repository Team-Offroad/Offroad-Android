package com.teamoffroad.feature.explore.presentation.model

data class FakeQuestModel(
    val name: String,
    val description: String,
    val task: String,
    val reward: String,
    val questProgressModel: QuestProgressModel,
) {
    companion object {
        private val dummyQuest = FakeQuestModel(
            name = "도심 속 공원 탐방",
            description = "연희동에서 카페를 탐방탐방 상세 정보 어쩌구 저쩌구 뭐가",
            task = "연희동 카페 6번 방문 시 나카나 디자인 체크",
            reward = "포인트 100원 적립",
            questProgressModel = QuestProgressModel(
                progress = 108,
                total = 178
            )
        )

        val dummyQuests = List(10) { dummyQuest }
    }
}

data class QuestProgressModel(
    val progress: Int,
    val total: Int,
)
