package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.presentation.component.dialog.DialogChangeButton
import com.teamoffroad.offroad.feature.home.R

@Composable
fun CompleteQuestDialog(
    completeQuests: List<String> = emptyList(),
    onClickCancel: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(14.dp),
    backgroundColor: Color = Main3,
    textColor: Color = Main2
) {
    Dialog(
        onDismissRequest = { onClickCancel() },
        properties = DialogProperties(dismissOnClickOutside = true, dismissOnBackPress = true)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(218.dp),
            shape = shape
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(vertical = 24.dp, horizontal = 40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.home_complete_quest),
                    color = textColor,
                    style = OffroadTheme.typography.title,
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                    color = textColor,
                    style = OffroadTheme.typography.textRegular,
                    text = if (completeQuests.isNotEmpty()) stringResource(
                        id = R.string.home_complete_quest_description,
                        completeQuests[0],
                        completeQuests.size - 1
                    ) else stringResource(
                        id = R.string.home_complete_quest_one_description,
                        completeQuests[0]
                    ),
                    textAlign = TextAlign.Center
                )

                DialogChangeButton(
                    text = stringResource(id = R.string.home_confirm),
                    textColor = White,
                    style = OffroadTheme.typography.hint,
                    backgroundColor = Main2,
                    borderColor = Main2,
                    onItemClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun CompleteQuestDialogPreview() {
    OffroadTheme {
        CompleteQuestDialog(emptyList(), {})
    }
}