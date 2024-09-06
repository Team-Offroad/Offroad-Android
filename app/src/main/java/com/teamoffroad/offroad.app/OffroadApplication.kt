package com.teamoffroad.offroad.app

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OffroadApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(applicationContext, com.teamoffroad.offroad.app.BuildConfig.KAKAO_CLIENT_ID)

        Log.d("asdsad", Utility.getKeyHash(this))
    }
}
