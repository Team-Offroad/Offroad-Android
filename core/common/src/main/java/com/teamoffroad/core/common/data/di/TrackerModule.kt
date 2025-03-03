package com.teamoffroad.core.common.data.di

import android.content.Context
import com.teamoffroad.core.common.data.tracker.AmplitudeTracker
import com.teamoffroad.core.common.domain.tracker.Tracker
import com.teamoffroad.offroad.core.common.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerModule {
    @Provides
    @Singleton
    fun provideTracker(
        @ApplicationContext context: Context,
    ): Tracker = AmplitudeTracker(context, BuildConfig.AMPLITUDE_KEY)
}
