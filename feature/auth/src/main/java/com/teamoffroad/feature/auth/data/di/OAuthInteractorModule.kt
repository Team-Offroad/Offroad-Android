package com.teamoffroad.feature.auth.data.di

import com.teamoffroad.feature.auth.data.repository.OAuthInteractorImpl
import com.teamoffroad.feature.auth.domain.repository.OAuthInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class OAuthInteractorModule {
    @Binds
    @ActivityScoped
    abstract fun provideOAuthRepository(
        oAuthInteractorImpl: OAuthInteractorImpl,
    ): OAuthInteractor
}