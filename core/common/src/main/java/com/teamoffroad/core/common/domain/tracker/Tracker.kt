package com.teamoffroad.core.common.domain.tracker

interface Tracker {
    fun trackEvent(
        eventName: String,
        properties: Map<String, Any?> = emptyMap(),
    )
}
