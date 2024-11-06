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
val OpticianSansRegular = FontFamily(Font(R.font.optician_sans, FontWeight.Normal))

@Stable
class OffroadTypography internal constructor(
    title: TextStyle,
    subtitle2Bold: TextStyle,
    subtitleReg: TextStyle,
    tooltipTitle: TextStyle,
    subtitle2Semibold: TextStyle,
    textBold: TextStyle,
    textRegular: TextStyle,
    textAuto: TextStyle,
    btnSmall: TextStyle,
    hint: TextStyle,
    tooltipNumber: TextStyle,
    textContentsSmall: TextStyle,
    textContents: TextStyle,
    profileTitle: TextStyle,
    bothLogin: TextStyle,
    bothBottomLabel: TextStyle,
    homeViewBothRecentNumBold: TextStyle,
    homeViewBothRecentNumRegular: TextStyle,
    homeViewBothUpcomingNumRegular: TextStyle,
    homeViewBothUpcomingNumBold: TextStyle,
    bothSubtitle3: TextStyle,
    tabBarMedi: TextStyle,
    boxMedi: TextStyle,
    marketing: TextStyle,
    questCompleted: TextStyle,
) {
    var title: TextStyle by mutableStateOf(title)
        private set
    var subtitle2Bold: TextStyle by mutableStateOf(subtitle2Bold)
        private set
    var subtitleReg: TextStyle by mutableStateOf(subtitleReg)
        private set
    var tooltipTitle: TextStyle by mutableStateOf(tooltipTitle)
        private set
    var subtitle2Semibold: TextStyle by mutableStateOf(subtitle2Semibold)
        private set
    var textBold: TextStyle by mutableStateOf(textBold)
        private set
    var textRegular: TextStyle by mutableStateOf(textRegular)
        private set
    var textAuto: TextStyle by mutableStateOf(textAuto)
        private set
    var btnSmall: TextStyle by mutableStateOf(btnSmall)
        private set
    var hint: TextStyle by mutableStateOf(hint)
        private set
    var tooltipNumber: TextStyle by mutableStateOf(tooltipNumber)
        private set
    var textContentsSmall: TextStyle by mutableStateOf(textContentsSmall)
        private set
    var textContents: TextStyle by mutableStateOf(textContents)
        private set
    var profileTitle: TextStyle by mutableStateOf(profileTitle)
        private set
    var bothLogin: TextStyle by mutableStateOf(bothLogin)
        private set
    var bothBottomLabel: TextStyle by mutableStateOf(bothBottomLabel)
        private set
    var bothRecentNumBold: TextStyle by mutableStateOf(homeViewBothRecentNumBold)
        private set
    var bothRecentNumRegular: TextStyle by mutableStateOf(homeViewBothRecentNumRegular)
        private set
    var bothUpcomingNumBold: TextStyle by mutableStateOf(homeViewBothUpcomingNumBold)
        private set
    var bothUpcomingNumRegular: TextStyle by mutableStateOf(homeViewBothUpcomingNumRegular)
        private set
    var bothSubtitle3: TextStyle by mutableStateOf(bothSubtitle3)
        private set
    var tabBarMedi: TextStyle by mutableStateOf(tabBarMedi)
        private set
    var boxMedi: TextStyle by mutableStateOf(boxMedi)
        private set
    var marketing: TextStyle by mutableStateOf(marketing)
        private set
    var questCompleted: TextStyle by mutableStateOf(questCompleted)
        private set

    fun copy(
        title: TextStyle = this.title,
        subtitle2Bold: TextStyle = this.subtitle2Bold,
        subtitleReg: TextStyle = this.subtitleReg,
        tooltipTitle: TextStyle = this.tooltipTitle,
        subtitle2Semibold: TextStyle = this.subtitle2Semibold,
        textBold: TextStyle = this.textBold,
        textRegular: TextStyle = this.textRegular,
        textAuto: TextStyle = this.textAuto,
        btnSmall: TextStyle = this.btnSmall,
        hint: TextStyle = this.hint,
        tooltipNumber: TextStyle = this.tooltipNumber,
        textContentsSmall: TextStyle = this.textContentsSmall,
        textContents: TextStyle = this.textContents,
        profileTitle: TextStyle = this.profileTitle,
        bothLogin: TextStyle = this.bothLogin,
        bothBottomLabel: TextStyle = this.bothBottomLabel,
        bothRecentNum: TextStyle = this.bothRecentNumBold,
        bothRecentNumRegular: TextStyle = this.bothRecentNumRegular,
        bothUpcomingNumBold: TextStyle = this.bothUpcomingNumBold,
        bothUpcomingNumRegular: TextStyle = this.bothUpcomingNumRegular,
        bothSubtitle3: TextStyle = this.bothSubtitle3,
        boxMedi: TextStyle = this.boxMedi,
        marketing: TextStyle = this.marketing,
        tabBarMedi: TextStyle = this.tabBarMedi,
        questCompleted: TextStyle = this.questCompleted,
    ): OffroadTypography = OffroadTypography(
        title,
        subtitle2Bold,
        subtitleReg,
        tooltipTitle,
        subtitle2Semibold,
        textBold,
        textRegular,
        textAuto,
        btnSmall,
        hint,
        tooltipNumber,
        textContentsSmall,
        textContents,
        profileTitle,
        bothLogin,
        bothBottomLabel,
        bothRecentNum,
        bothRecentNumRegular,
        bothUpcomingNumBold,
        bothUpcomingNumRegular,
        bothSubtitle3,
        boxMedi,
        marketing,
        tabBarMedi,
        questCompleted,
    )

    fun update(other: OffroadTypography) {
        title = other.title
        subtitle2Bold = other.subtitle2Bold
        subtitleReg = other.subtitleReg
        tooltipTitle = other.tooltipTitle
        subtitle2Semibold = other.subtitle2Semibold
        textBold = other.textBold
        textRegular = other.textRegular
        textAuto = other.textAuto
        btnSmall = other.btnSmall
        hint = other.hint
        tooltipNumber = other.tooltipNumber
        textContentsSmall = other.textContentsSmall
        textContents = other.textContents
        profileTitle = other.profileTitle
        bothLogin = other.bothLogin
        bothBottomLabel = other.bothBottomLabel
        bothRecentNumBold = other.bothRecentNumBold
        bothRecentNumRegular = other.bothRecentNumRegular
        bothUpcomingNumBold = other.bothUpcomingNumBold
        bothUpcomingNumRegular = other.bothUpcomingNumRegular
        bothSubtitle3 = other.bothSubtitle3
        boxMedi = other.boxMedi
        marketing = other.marketing
        tabBarMedi = other.tabBarMedi
        questCompleted = other.questCompleted
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
        subtitle2Semibold = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        textBold = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        textRegular = TextStyle(
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
        textContentsSmall = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp
        ),
        textContents = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
        ),
        profileTitle = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        bothLogin = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        ),
        bothBottomLabel = TextStyle(
            fontFamily = OpticianSansRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        homeViewBothRecentNumBold = TextStyle(
            fontFamily = OpticianSansRegular,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        ),
        homeViewBothRecentNumRegular = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        ),
        homeViewBothUpcomingNumBold = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        ),
        homeViewBothUpcomingNumRegular = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp
        ),
        bothSubtitle3 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        ),
        tabBarMedi = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp
        ),
        boxMedi = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),
        marketing = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        questCompleted = TextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        ),
    )
}
