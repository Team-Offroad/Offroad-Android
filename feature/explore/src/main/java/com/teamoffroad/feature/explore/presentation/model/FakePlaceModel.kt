package com.teamoffroad.feature.explore.presentation.model

data class FakePlaceModel(
    val id: Long,
    val name: String,
    val town: String,
    val address: String,
    val shortIntroduction: String,
    val placeCategory: PlaceCategory,
    val categoryImageUrl: String,
    val visitCount: Int,
) {
    companion object {
        private val dummyPlace = FakePlaceModel(
            id = 1,
            name = "카페",
            town = "시간이 머무는 마을",
            address = "서울특별시 강남구 역삼동 123-45",
            shortIntroduction = "강남 휘낭시에 맛도리 카페",
            placeCategory = PlaceCategory.CAFFE,
            categoryImageUrl = "https://github.com/user-attachments/assets/443755cf-5c0f-4c5d-959e-8eadb86bf6e8",
            visitCount = 96,
        )
        private val dummyPlace2 = FakePlaceModel(
            id = 1,
            name = "카페",
            town = "시간이 머무는 마을",
            address = "서울특별시 강남구 역삼동 123-45",
            shortIntroduction = "강남 휘낭시에 맛도리 카페",
            placeCategory = PlaceCategory.CAFFE,
            categoryImageUrl = "https://github.com/user-attachments/assets/443755cf-5c0f-4c5d-959e-8eadb86bf6e8",
            visitCount = 0,
        )
        val dummyPlaces = listOf(
            dummyPlace, dummyPlace2, dummyPlace, dummyPlace2, dummyPlace, dummyPlace2, dummyPlace, dummyPlace2
        )
    }
}
