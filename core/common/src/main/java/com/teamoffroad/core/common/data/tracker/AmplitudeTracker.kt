package com.teamoffroad.core.common.data.tracker

import android.content.Context
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import com.amplitude.android.TrackingOptions
import com.amplitude.android.autocaptureOptions
import com.teamoffroad.core.common.domain.tracker.Tracker

class AmplitudeTracker(
    context: Context,
    apiKey: String,
) : Tracker {
    private val amplitude: Amplitude? =
        if (apiKey.isNotEmpty()) {
            Amplitude(
                Configuration(
                    apiKey = apiKey,
                    context = context,
                    flushQueueSize = FLUSH_QUEUE_SIZE,
                    flushIntervalMillis = FLUSH_INTERVAL_MILLIS,
                    minTimeBetweenSessionsMillis = MIN_SESSION_INTERVAL,
                    trackingOptions =
                        TrackingOptions().apply {
                            disableCarrier()
                            disableLanguage()
                        },
                    autocapture =
                        autocaptureOptions {
                            +sessions
                            +appLifecycles
                            +screenViews
                        },
                ),
            )
        } else {
            null
        }

    override fun trackEvent(
        eventName: String,
        properties: Map<String, Any?>,
    ) {
        amplitude?.track(eventName, properties)
    }

    companion object {
        private const val FLUSH_QUEUE_SIZE = 50
        private const val FLUSH_INTERVAL_MILLIS = 10_000
        private const val MIN_SESSION_INTERVAL = 5_000L
    }
}
