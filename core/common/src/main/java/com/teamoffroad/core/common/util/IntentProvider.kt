package com.teamoffroad.core.common.util

import android.content.Intent

interface IntentProvider {
    fun getIntent(): Intent
}