package com.teamoffroad.feature.main.intentprovider

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.teamoffroad.core.common.util.IntentProvider
import com.teamoffroad.feature.main.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IntentProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : IntentProvider {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun getIntent(): Intent =
        MainActivity.newInstance(context)
}