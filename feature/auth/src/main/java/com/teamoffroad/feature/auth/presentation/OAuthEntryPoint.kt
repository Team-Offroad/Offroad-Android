package com.teamoffroad.feature.auth.presentation

import com.teamoffroad.feature.auth.domain.repository.OAuthInteractor
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface OAuthEntryPoint {
    fun getOAuthInteractor(): OAuthInteractor
}