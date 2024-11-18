package com.teamoffroad.feature.main.di

import com.teamoffroad.core.common.intentProvider.IntentProvider
import com.teamoffroad.feature.main.intentprovider.IntentProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IntentModule {
    @Binds
    @Singleton
    abstract fun bindsIntentProvider(intentProvider: IntentProviderImpl): IntentProvider
}