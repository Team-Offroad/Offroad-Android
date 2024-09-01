package com.teamoffroad.feature.mypage.presentation.component

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.mypage.presentation.model.GainedEmblem

@Composable
fun GainedEmblemsItems(
    modifier: Modifier = Modifier,
    gainedEmblemsList: List<GainedEmblem>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ListBg),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }
        items(gainedEmblemsList) { it ->
            EmblemContainer(
                title = it.emblemTitle,
                subTitle = it.emblemSubtitle,
                isNew = it.isNew,
                isLock = it.isLock
            )
        }
        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}