package com.teamoffroad.core.common.data.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoneAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Auth