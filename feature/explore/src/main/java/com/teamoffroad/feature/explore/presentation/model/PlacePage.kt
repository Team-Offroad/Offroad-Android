package com.teamoffroad.feature.explore.presentation.model

enum class PlacePage(val page: Int) {
    TOTAL(0), UNVISITED(1), NONE(-1)
    ;

    companion object {
        fun from(value: Int): PlacePage {
            return when (value) {
                0 -> TOTAL
                1 -> UNVISITED
                else -> NONE
            }
        }
    }
}