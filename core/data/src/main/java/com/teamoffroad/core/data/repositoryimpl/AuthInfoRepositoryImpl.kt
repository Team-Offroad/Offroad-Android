package com.teamoffroad.core.data.repositoryimpl

import com.teamoffroad.core.data.repository.AuthInfoRepository
import com.teamoffroad.core.datastore.datasource.AuthPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

internal class AuthInfoRepositoryImpl @Inject constructor(
    @Named("auth_datasource") private val dataSource: AuthPreferencesDataSource
) : AuthInfoRepository {

    override fun getAccessToken(): Flow<String> =
        dataSource.accessToken


    override fun getRefreshToken(): Flow<String> =
        dataSource.refreshToken

    override fun getAutoLogin(): Flow<Boolean> =
        dataSource.autoLogin

    override suspend fun saveAccessToken(accessToken: String) =
        dataSource.setAccessToken(accessToken)

    override suspend fun saveRefreshToken(refreshToken: String) =
        dataSource.setRefreshToken(refreshToken)

    override suspend fun saveAutoLogin(autoLogin: Boolean) =
        dataSource.setAutoLogin(autoLogin)
}