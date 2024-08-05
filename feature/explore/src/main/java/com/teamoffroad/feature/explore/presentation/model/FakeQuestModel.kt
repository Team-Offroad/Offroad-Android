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
                total = 178,
            )
        )

        private val dummyQuest2 = FakeQuestModel(
            name = "미술관 속 숨바꼭질",
            description = "도심 속 미술관에서 숨겨진 보물을 찾아 떠나는 여정! 다양한 전시회를 탐방하며 정보를 모으세요.",
            task = "도심 미술관 5곳 방문하여 숨겨진 QR코드 스캔",
            reward = "무료 전시회 입장권 1매",
            questProgressModel = QuestProgressModel(
                progress = 30,
                total = 30,
            )
        )

        val dummyQuests =
            listOf(dummyQuest, dummyQuest2, dummyQuest, dummyQuest2, dummyQuest, dummyQuest2, dummyQuest, dummyQuest2, dummyQuest, dummyQuest2)
    }
}

data class QuestProgressModel(
    val progress: Int,
    val total: Int,
    val isCompleted: Boolean = progress == total,
)
