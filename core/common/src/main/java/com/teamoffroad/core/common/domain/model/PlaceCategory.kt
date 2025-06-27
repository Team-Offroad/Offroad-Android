package com.teamoffroad.core.common.domain.model

enum class PlaceCategory(
    val krName: String,
) {
    CAFFE("카페"),
    PARK("공원"),
    RESTAURANT("식당"),
    CULTURE("문화"),
    SPORT("스포츠"),
    NONE("없음"),
    ;

    companion object {
        fun fromKrName(krName: String): PlaceCategory = entries.find { it.krName == krName } ?: NONE
    }
}
