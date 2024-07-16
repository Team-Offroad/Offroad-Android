package com.teamoffroad.feature.home.presentation

import com.google.gson.Gson
import retrofit2.HttpException

fun getErrorMessage(t: Any): String {
    val gson = Gson()

    return if (t is HttpException) {
        val errorBody = t.response()?.errorBody()?.string()
        errorBody?.let {
            gson.fromJson(it, ErrorMessageModel::class.java).message
        } ?: t.message.toString()
    } else t.toString()
}