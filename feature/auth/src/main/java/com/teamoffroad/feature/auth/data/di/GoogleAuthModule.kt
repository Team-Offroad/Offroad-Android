package com.teamoffroad.feature.auth.data.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.teamoffroad.offroad.feature.auth.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleAuthModule {

    @Provides
    @Singleton
    fun provideGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(BuildConfig.GOOGLE_CLIENT_ID)
            .requestEmail()
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(@ApplicationContext context: Context, options: GoogleSignInOptions): GoogleSignInClient {
        return GoogleSignIn.getClient(context, options)
    }
}
