package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import kotlin.reflect.KFunction1

@Composable
fun MarketingInfoDialog(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(14.dp),
    onClick: KFunction1<Boolean, Unit>,
    onClickCancel: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onClickCancel() },
        properties = DialogProperties(dismissOnClickOutside = true, dismissOnBackPress = true)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(shape = shape, color = Main3),
        ) {
            Column(
                modifier = modifier
                    .padding(vertical = 22.dp, horizontal = 40.dp)
            ) {
                Text(
                    text = "마케팅 정보 수신 동의",
                    color = Main2,
                    style = OffroadTheme.typography.title,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "서비스 제공 및 이용과 관련하여 오프로드가 취득\n" +
                            "한 개인정보는 “개인정보보호법” 및 “정보통신망\n" +
                            "이용촉진 및 정보보호 등에 관한 법률” 등 정보통\n" +
                            "신서비스제공자가 준수하여야 할 관련 법령상의\n" +
                            "개인정보 보호 규정을 준수합니다." +
                            "\n\n" +
                            "1.  고객이 수집 및 이용에 동의한 개인정보를 활\n" +
                            "     용하여, 전자적 전송 매체(E-mail)를 통하여\n" +
                            "     발송됩니다." +
                            "\n" +
                            "2.  발송되는 마케팅 정보는 수신자에게 오프로드\n" +
                            "     및 제 3자의 상품 또는 서비스에 대한 혜택 정\n" +
                            "     보, 각종 이벤트 정보, 개인 맞춤형 광고 정보\n" +
                            "     등 광고성 정보로 관련 법의 규정을 준수하여 \n" +
                            "     발송됩니다. 단, 광고성 정보 이외에 의무적으\n" +
                            "     로 안내되어야 하는 정보성 내용은 수신동의 \n" +
                            "     여부와 무관하게 제공됩니다.",
                    color = Gray400,
                    style = OffroadTheme.typography.marketing,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 33.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    AgreeButton(
                        text = "비동의",
                        onClick = {
                            onClickCancel()
                            onClick(false)
                        },
                        textColor = Main2,
                        backgroundColor = Main3,
                        modifier = Modifier.weight(1f)
                    )
                    AgreeButton(
                        text = "동의",
                        onClick = {
                            onClickCancel()
                            onClick(true)
                        },
                        textColor = White,
                        backgroundColor = Main2,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun AgreeButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    textColor: Color,
    backgroundColor: Color,
) {
    Text(text = text,
        color = textColor,
        style = OffroadTheme.typography.btnSmall,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(5.dp)
            )
            .border(width = 1.dp, shape = RoundedCornerShape(5.dp), color = Main2)
            .padding(vertical = 14.dp, horizontal = 38.dp)
            .clickableWithoutRipple { onClick() })
}