package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.model.SettingItem

@Composable
fun SettingItems(
    modifier: Modifier = Modifier,
    settingItemList: List<SettingItem>
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Main1),
        state = listState,
        verticalArrangement = Arrangement.Top
    ) {
        items(settingItemList.size) { item ->
            SettingContainer(
                title = settingItemList[item].title,
                isImportant = settingItemList[item].isImportant,
                onClick = settingItemList[item].onClick
            )
        }
    }
}