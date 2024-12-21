package com.teamoffroad.characterchat.data.remote.service

import com.teamoffroad.characterchat.data.remote.request.CharacterChatSendRequestDto
import com.teamoffroad.characterchat.data.remote.response.CharacterChatLastUnreadResponseDto
import com.teamoffroad.characterchat.data.remote.response.CharacterChatResponseDto
import com.teamoffroad.core.common.data.remote.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatService {

    @GET("chats")
    suspend fun getChats(
        @Query("characterId") characterId: Int?,
        @Query("limit") limit: Int,
        @Query("cursor") cursor: Long?,
    ): BaseResponse<List<CharacterChatResponseDto>>

    @POST("chats")
    suspend fun sendChat(
        @Query("characterId") characterId: Int?,
        @Body request: CharacterChatSendRequestDto,
    ): BaseResponse<CharacterChatResponseDto>

    @GET("chats/last-unread")
    suspend fun getLastUnread(): BaseResponse<CharacterChatLastUnreadResponseDto>
}
