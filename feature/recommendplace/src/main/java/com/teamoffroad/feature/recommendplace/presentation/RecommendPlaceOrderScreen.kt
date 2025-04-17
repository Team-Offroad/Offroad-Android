package com.teamoffroad.feature.recommendplace.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.recommendplace.presentation.component.PlaceType
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceOrderDialog
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceOrderEtc
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceOrderLocation
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceSelect
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceOrder(
    navigateToBack: () -> Unit,
) {
    var selectedType by remember { mutableStateOf<PlaceType?>(null) }
    var showSelectedTypeWarning by remember { mutableStateOf(false) }
    var locationText by remember { mutableStateOf("") }
    var showLocationTextWarning by remember { mutableStateOf(false) }
    var etcText by remember { mutableStateOf("") }
    var etcTextFieldIsFocused by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val showBackConfirmDialog = remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    var scrollHeight by remember { mutableStateOf(0.dp) }
    var keyboardHeight by remember { mutableIntStateOf(0) }

    LaunchedEffect(keyboardHeight, etcTextFieldIsFocused) {
        if (keyboardHeight > 0 && etcTextFieldIsFocused) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }

    Column(
        modifier = Modifier
            .navigationPadding()
            .background(color = Main1)
            .fillMaxSize()
            .actionBarPadding()
    ) {
        Column(modifier = Modifier.clickableWithoutRipple { focusManager.clearFocus() }) {
            RecommendPlaceOrderHeader(
                selectedType = selectedType,
                locationText = locationText,
                etcText = etcText,
                showBackConfirmDialogSetter = { showBackConfirmDialog.value = it },
                navigateToBack = navigateToBack
            )
            HorizontalDivider(color = Gray100)
        }

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f)
        ) {
            RecommendPlaceSelect(
                selectedType = selectedType,
                onSelectType = {
                    selectedType = it
                    showSelectedTypeWarning = false
                },
                showWarning = showSelectedTypeWarning
            )
            RecommendPlaceOrderLocation(
                locationText = locationText,
                onLocationTextChanged = { locationText = it },
                showWarning = showLocationTextWarning
            )
            RecommendPlaceOrderEtc(
                setScrollHeight = { scrollHeight = it },
                setKeyboardHeight = { keyboardHeight = it },
                etcText = etcText,
                onEtcTextChanged = { etcText = it },
                setEtcTextFieldIsFocused = { etcTextFieldIsFocused = it }
            )

            if (keyboardHeight > 0 && etcTextFieldIsFocused) Spacer(
                modifier = Modifier.height(
                    scrollHeight
                )
            )
        }

        Column {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickableWithoutRipple {
                        // 다 초기화
                        selectedType = null
                        locationText = ""
                        etcText = ""
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.recommend_place_order_question_undo),
                    style = OffroadTheme.typography.textBold,
                    color = Gray400
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_recommend_place_undo),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            RecommendPlaceOrderButton(
                isEnabled = selectedType != null && locationText.isNotEmpty(),
                submitOrder = {
                    if (selectedType == null) {
                        showSelectedTypeWarning = true
                    }
                    if (locationText.isEmpty()) {
                        showLocationTextWarning = true
                    }
                    // 주문서 등록하기
                }
            )
        }

    }

    if (showBackConfirmDialog.value) {
        RecommendPlaceOrderDialog(
            navigateToBack = navigateToBack,
            onClickCancel = {
                showBackConfirmDialog.value = false
            }
        )
    }
}

@Composable
fun RecommendPlaceOrderButton(
    isEnabled: Boolean,
    submitOrder: () -> Unit
) {
    Text(
        text = stringResource(id = R.string.recommend_place_order_question_button),
        color = White,
        style = OffroadTheme.typography.textRegular,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .then(
                if (isEnabled) Modifier.border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = Main2
                ) else Modifier
            )
            .background(
                shape = RoundedCornerShape(6.dp),
                color = if (isEnabled) Main2 else Black25
            )
            .padding(horizontal = 110.dp, 14.dp)
            .clickableWithoutRipple { submitOrder() }
    )
}

@Composable
fun RecommendPlaceOrderHeader(
    selectedType: PlaceType?,
    locationText: String,
    etcText: String,
    showBackConfirmDialogSetter: (Boolean) -> Unit,
    navigateToBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = com.teamoffroad.offroad.core.designsystem.R.drawable.ic_navigate_back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Main2),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 6.dp)
                .size(48.dp)
                .padding(12.dp)
                .clickableWithoutRipple {
                    if (selectedType != null || locationText.isNotEmpty() || etcText.isNotEmpty()) showBackConfirmDialogSetter(
                        true
                    )
                    else navigateToBack()
                },
        )

        Text(
            text = stringResource(id = R.string.recommend_place_order),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            style = OffroadTheme.typography.bothLogin,
            color = Main2
        )
    }
}