package com.teamoffroad.feature.explore.presentation.model

enum class PlacePage(val page: Int) {
    UNVISITED(0), TOTAL(1), NONE(-1)
    ;

    companion object {
        fun from(page: Int): PlacePage {
            return entries.find { it.page == page } ?: NONE
        }
    }
}
