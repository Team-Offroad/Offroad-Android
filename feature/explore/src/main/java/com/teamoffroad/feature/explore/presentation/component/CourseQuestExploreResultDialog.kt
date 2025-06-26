package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.common.util.applyBold
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun CourseQuestExploreResultDialog(
    state: ExploreAuthState,
    title: String = "",
    text: String = "",
    onDismissClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissClick,
        properties = DialogProperties(dismissOnClickOutside = false),
    ) {
        Box(
            modifier =
                Modifier
                    .width(312.dp)
                    .wrapContentHeight()
                    .background(Main3, shape = RoundedCornerShape(14.dp)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier =
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(top = 34.dp),
            ) {
                Text(
                    text =
                        if (state is ExploreAuthState.Success) {
                            stringResource(R.string.explore_dialog_success)
                        } else {
                            stringResource(R.string.explore_dialog_failed)
                        },
                    style = OffroadTheme.typography.title,
                    color = Main2,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = text.applyBold(),
                    color = Main2,
                    textAlign = TextAlign.Center,
                    style = OffroadTheme.typography.textRegular,
                )
                Spacer(modifier = Modifier.height(14.dp))
            }
            Box(
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 40.dp)
                        .padding(bottom = 28.dp),
            ) {
                Box(
                    modifier =
                        Modifier
                            .background(
                                color = Main2,
                                shape = RoundedCornerShape(6.dp),
                            ).clickable(onClick = onDismissClick)
                            .fillMaxWidth()
                            .height(44.dp)
                            .align(Alignment.BottomCenter),
                ) {
                    Text(
                        text =
                            when (state) {
                                is ExploreAuthState.Success -> stringResource(R.string.explore_dialog_success_button)
                                else -> stringResource(R.string.explore_dialog_failed_button)
                            },
                        textAlign = TextAlign.Center,
                        style = OffroadTheme.typography.btnSmall,
                        color = White,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CourseQuestExploreResultDialogPreview() {
    OffroadTheme {
        CourseQuestExploreResultDialog(
            state = ExploreAuthState.Success(),
            title = "퀘스트 완료",
            text = "축하합니다! 퀘스트를 성공적으로 완료했습니다.",
            onDismissClick = {},
        )
    }
}
