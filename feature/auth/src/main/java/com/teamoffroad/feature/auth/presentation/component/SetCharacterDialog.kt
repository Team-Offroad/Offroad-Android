package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.offroad.feature.auth.R

@Composable
fun SetCharacterDialog(
    character: Character,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
    val boldCharacterName = buildAnnotatedString {
        val characterName = character.name
        val fullText = stringResource(R.string.auth_character_question_label, characterName)
        append(fullText)
        val nameStartIndex = fullText.indexOf(characterName)
        val nameEndIndex = nameStartIndex + characterName.length
        addStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold),
            start = nameStartIndex,
            end = nameEndIndex
        )
    }
    
    Box {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(dismissOnClickOutside = false),
            title = null,
            text = {
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(
                            R.string.auth_character_question_title,
                            character.name
                        ),
                        style = OffroadTheme.typography.title,
                        color = Main2,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        text = boldCharacterName,
                        style = OffroadTheme.typography.textRegular,
                        color = Main2,
                        textAlign = TextAlign.Center
                    )
                }
            },
            dismissButton = {
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .fillMaxWidth(0.48f)
                        .padding(horizontal = 6.dp)
                        .clickable { onDismissRequest() }
                        .background(color = Color.Transparent, shape = RoundedCornerShape(6.dp))
                        .border(1.dp, Main2, RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.auth_character_question_deny),
                        color = Main2,
                        style = OffroadTheme.typography.btnSmall,
                        textAlign = TextAlign.Center,
                    )
                }
            },
            confirmButton = {
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .fillMaxWidth(0.48f)
                        .padding(horizontal = 6.dp)
                        .clickable { onConfirm() }
                        .background(color = Main2, shape = RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.auth_character_question_accept),
                        color = Color.White,
                        style = OffroadTheme.typography.btnSmall,
                        textAlign = TextAlign.Center,
                    )
                }
            },
            shape = RoundedCornerShape(12.dp),
            containerColor = (Main3),
        )
    }
}