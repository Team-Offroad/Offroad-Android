package com.teamoffroad.core.common.data.di

import com.teamoffroad.core.common.data.repository.TokenRepositoryImpl
import com.teamoffroad.core.common.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl,
    ): TokenRepository
}
