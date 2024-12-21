package com.teamoffroad.offroad.app

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.teamoffroad.core.common.util.ActivityLifecycleHandler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OffroadApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setKakaoSdk()
        registerActivityLifecycleCallbacks(ActivityLifecycleHandler())
    }

    private fun setKakaoSdk() {
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}
