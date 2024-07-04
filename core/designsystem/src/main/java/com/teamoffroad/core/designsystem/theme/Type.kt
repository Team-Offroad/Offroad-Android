package com.teamoffroad.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.teamoffroad.offroad.core.designsystem.R

val PretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val PretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))

@Stable
class OffroadTypography internal constructor(
    title: TextStyle,
    subtitle2Bold: TextStyle,
    subtitle2Semibold: TextStyle,
    subtitleReg: TextStyle,
    tooltipTitle: TextStyle,
    tooltipDescription: TextStyle,
    textBold: TextStyle,
    text: TextStyle,
    textAuto: TextStyle,
    btnSmall: TextStyle,
    hint: TextStyle,
    tooltipNumber: TextStyle,
    tooltipAdrs: TextStyle
) {
    var title: TextStyle by mutableStateOf(title)
        private set
    var subtitle2Bold: TextStyle by mutableStateOf(subtitle2Bold)
        private set
    var subtitle2Semibold: TextStyle by mutableStateOf(subtitle2Semibold)
        private set
    var subtitleReg: TextStyle by mutableStateOf(subtitleReg)
        private set
    var tooltipTitle: TextStyle by mutableStateOf(tooltipTitle)
        private set
    var tooltipDescription: TextStyle by mutableStateOf(tooltipDescription)
        private set
    var textBold: TextStyle by mutableStateOf(textBold)
        private set
    var text: TextStyle by mutableStateOf(text)
        private set
    var textAuto: TextStyle by mutableStateOf(textAuto)
        private set
    var btnSmall: TextStyle by mutableStateOf(btnSmall)
        private set
    var hint: TextStyle by mutableStateOf(hint)
        private set
    var tooltipNumber: TextStyle by mutableStateOf(tooltipNumber)
        private set
    var tooltipAdrs: TextStyle by mutableStateOf(tooltipAdrs)
        private set

    fun copy(
        title: TextStyle = this.title,
        subtitle2Bold: TextStyle = this.subtitle2Bold,
        subtitle2Semibold: TextStyle = this.subtitle2Semibold,
        subtitleReg: TextStyle = this.subtitleReg,
        tooltipTitle: TextStyle = this.tooltipTitle,
        tooltipDescription: TextStyle = this.tooltipDescription,
        textBold: TextStyle = this.textBold,
        text: TextStyle = this.text,
        textAuto: TextStyle = this.textAuto,
        btnSmall: TextStyle = this.btnSmall,
        hint: TextStyle = this.hint,
        tooltipNumber: TextStyle = this.tooltipNumber,
        tooltipAdrs: TextStyle = this.tooltipAdrs,
    ): OffroadTypography = OffroadTypography(
        title,
        subtitle2Bold,
        subtitle2Semibold,
        subtitleReg,
        tooltipTitle,
        tooltipDescription,
        textBold,
        text,
        textAuto,
        btnSmall,
        hint,
        tooltipNumber,
        tooltipAdrs
    )

    fun update(other: OffroadTypography) {
        title = other.title
        subtitle2Bold = other.subtitle2Bold
        subtitle2Semibold = other.subtitle2Semibold
        subtitleReg = other.subtitleReg
        tooltipTitle = other.tooltipTitle
        tooltipDescription = other.tooltipDescription
        textBold = other.textBold
        text = other.text
        textAuto = other.textAuto
        btnSmall = other.btnSmall
        hint = other.hint
        tooltipNumber = other.tooltipNumber
        tooltipAdrs = other.tooltipAdrs
    }
}

@Composable
fun offroadTypography(): OffroadTypography {
    return OffroadTypography(
        title = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),
        subtitle2Bold = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        ),
        subtitle2Semibold = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        subtitleReg = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        ),
        tooltipTitle = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        ),
        tooltipDescription = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp
        ),
        textBold = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        text = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        textAuto = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        btnSmall = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp
        ),
        hint = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),
        tooltipNumber = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp
        ),
        tooltipAdrs = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp
        )
    )
}
