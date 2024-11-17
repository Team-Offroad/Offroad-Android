package com.teamoffroad.characterchat.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.characterchat.presentation.KEYBOARD_LOADING_OFFSET
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatType.ORB_CHARACTER
import com.teamoffroad.characterchat.presentation.model.ChatType.USER
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CharacterChats(
    modifier: Modifier = Modifier,
    characterName: String,
    arrangedChats: Map<LocalDate, List<ChatModel>>,
    bottomPadding: Int = 0,
    listState: LazyListState = rememberLazyListState(),
) {
    val animatedHeight = animateDpAsState(targetValue = (188 + bottomPadding).dp, label = "")
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(bottomPadding) {
        coroutineScope.launch {
            delay(KEYBOARD_LOADING_OFFSET)
            listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
        }
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        arrangedChats.forEach { (date, chats) ->
            item {
                DateDivider(date = date)
            }
            chats.forEach { chat ->
                item {
                    when (chat.chatType) {
                        USER -> UserChatBox(text = chat.text, time = chat.time)
                        ORB_CHARACTER -> CharacterChatBox(name = characterName, text = chat.text, time = chat.time)
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
        item {
            Spacer(
                modifier = Modifier.height(animatedHeight.value)
            )
        }
    }
}
