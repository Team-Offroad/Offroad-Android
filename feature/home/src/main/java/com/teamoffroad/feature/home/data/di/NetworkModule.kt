package com.teamoffroad.feature.home.data.di

import com.teamoffroad.feature.home.data.remote.service.DummyUserService
import com.teamoffroad.feature.home.data.remote.service.EmblemService
import com.teamoffroad.feature.home.domain.repository.EmblemRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): DummyUserService {
        return retrofit.create(DummyUserService::class.java)
    }

    @Provides
    @Singleton
    fun provideEmblemService(retrofit: Retrofit): EmblemService {
        return retrofit.create(EmblemService::class.java)
    }

//    @Module
//    @InstallIn(SingletonComponent::class)
//    interface RepositoryModule {
//        @Singleton
//        @Binds
//        fun bindsEmblemRepository(emblemRepository: EmblemRepository):
//                EmblemRepository
//    }
}
