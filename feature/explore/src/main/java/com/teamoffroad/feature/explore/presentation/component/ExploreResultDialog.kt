package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreResultDialog(
    errorType: ExploreAuthState,
    previousText: String = "",
    nextText: String = "",
    boldText: String = "",
    content: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = false),
    ) {
        Box(
            modifier = Modifier
                .width(312.dp)
                .wrapContentHeight()
                .background(Main3, shape = RoundedCornerShape(14.dp))
        ) {
            if (errorType is ExploreAuthState.Success) {
                Image(
                    painter = painterResource(id = R.drawable.bg_explore_dialog),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 34.dp),
            ) {
                Text(
                    text = if (errorType is ExploreAuthState.Success) {
                        stringResource(R.string.explore_dialog_success)
                    } else {
                        stringResource(R.string.explore_dialog_failed)
                    },
                    style = OffroadTheme.typography.title,
                    color = Main2,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = buildAnnotatedString {
                        append(previousText)
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append(boldText)
                        pop()
                        append(nextText)
                    },
                    color = Main2,
                    textAlign = TextAlign.Center,
                    style = OffroadTheme.typography.textRegular
                )
                Spacer(modifier = Modifier.height(14.dp))
                content()
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 28.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Main2,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable(onClick = onDismissRequest)
                        .fillMaxWidth()
                        .height(44.dp)
                        .align(Alignment.BottomCenter),
                ) {
                    Text(
                        text = when (errorType) {
                            is ExploreAuthState.Success -> stringResource(R.string.explore_dialog_success_button)
                            else -> stringResource(R.string.explore_dialog_failed_button)
                        },
                        textAlign = TextAlign.Center,
                        style = OffroadTheme.typography.btnSmall,
                        color = White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}