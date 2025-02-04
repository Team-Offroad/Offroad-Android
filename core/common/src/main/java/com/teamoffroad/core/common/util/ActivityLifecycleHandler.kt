package com.teamoffroad.core.common.util

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityLifecycleHandler : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
    }

    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityResumed(p0: Activity) {
        isAppInForeground = true
    }

    override fun onActivityPaused(p0: Activity) {
        isAppInForeground = false
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(p0: Activity) {
    }

    companion object {
        var isAppInForeground = false
    }
}