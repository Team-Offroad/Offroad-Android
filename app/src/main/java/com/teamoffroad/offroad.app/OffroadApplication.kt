package com.teamoffroad.offroad.app

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OffroadApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        setKakaoSdk()
    }

    private fun setKakaoSdk() {
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)
    }
}
