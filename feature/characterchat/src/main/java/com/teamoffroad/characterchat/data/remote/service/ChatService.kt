package com.teamoffroad.characterchat.data.remote.service

import com.teamoffroad.characterchat.data.remote.request.CharacterChatSendRequestDto
import com.teamoffroad.characterchat.data.remote.response.CharacterChatResponseDto
import com.teamoffroad.core.common.data.remote.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatService {

    @GET("chats")
    suspend fun getChats(): BaseResponse<List<CharacterChatResponseDto>>

    @POST("chats")
    suspend fun sendChat(
        @Body request: CharacterChatSendRequestDto,
    ): BaseResponse<CharacterChatResponseDto>
}
