package com.teamoffroad.characterchat.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatType.ORB_CHARACTER
import com.teamoffroad.characterchat.presentation.model.ChatType.USER
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CharacterChats(
    modifier: Modifier = Modifier,
    characterName: String,
    arrangedChats: Map<LocalDate, List<ChatModel>>,
    bottomPadding: Int = 0,
    isChatting: Boolean = false,
    isSending: Boolean = false,
    isLoadable: Boolean = true,
    updateChats: () -> Unit,
    updateIsChatting: (Boolean) -> Unit,
) {
    val animatedHeight = animateDpAsState(targetValue = (188 + bottomPadding).dp, label = "")
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var isInitialComposition = remember { true }

    fun findIndexByKey(key: Any?): Int {
        return listState.layoutInfo.visibleItemsInfo.find { it.key == key }?.index ?: 0
    }

    LaunchedEffect(arrangedChats.values.firstOrNull()?.first()?.id ?: 0, isInitialComposition) {
        if (!isInitialComposition) {
            val keyToScroll = arrangedChats.values.flatten().firstOrNull()?.id ?: 0
            val indexToScroll = findIndexByKey(keyToScroll)

            if (indexToScroll >= 0) {
                coroutineScope.launch {
                    listState.scrollToItem(indexToScroll)
                }
            }
        }
    }

    LaunchedEffect(listState, arrangedChats, isLoadable) {
        snapshotFlow { listState.firstVisibleItemIndex }.distinctUntilChanged().collect { firstVisibleItemIndex ->
            if (isLoadable && arrangedChats.isNotEmpty() && firstVisibleItemIndex < LOAD_THRESHOLD) {
                updateChats()
            }
        }
    }

    LaunchedEffect(bottomPadding) {
        if (isChatting) {
            coroutineScope.launch {
                delay(KEYBOARD_LOADING_OFFSET)
                listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
            }
        }
    }

    LaunchedEffect(arrangedChats.values.lastOrNull()?.last()?.id ?: 0, isInitialComposition) {
        if (arrangedChats.values.flatten().isNotEmpty() && isInitialComposition) {
            coroutineScope.launch {
                listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
                delay(INITIAL_LOADING_OFFSET)
                isInitialComposition = false
            }
        }
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        arrangedChats.forEach { (date, chats) ->
            item {
                DateDivider(date = date)
            }
            chats.forEach { chat ->
                item(key = chat.id) {
                    when (chat.chatType) {
                        USER -> UserChatBox(text = chat.text, time = chat.time)
                        ORB_CHARACTER -> CharacterChatBox(name = characterName, text = chat.text, time = chat.time)
                    }
                }
            }
            item {
                CharacterChatLoadingBox(name = characterName, time = chats.last().time, isSending = isSending)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
        item {
            ChatButton(
                isVisible = !isChatting && !isSending,
                onClick = {
                    updateIsChatting(true)
                },
            )
            Spacer(
                modifier = Modifier.height(animatedHeight.value)
            )
        }
    }
}

private const val LOAD_THRESHOLD = 10
private const val KEYBOARD_LOADING_OFFSET = 50L
private const val INITIAL_LOADING_OFFSET = 2000L
