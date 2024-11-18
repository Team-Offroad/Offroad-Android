package com.teamoffroad.feature.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamoffroad.core.designsystem.component.ChangeBottomBarColor
import com.teamoffroad.core.designsystem.component.StaticAnimationWrapper
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.BottomBarInactive
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4_80
import com.teamoffroad.feature.main.MainNavTab
import com.teamoffroad.offroad.feature.main.R
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun MainBottomBar(
    modifier: Modifier = Modifier,
    visible: Boolean,
    tabs: PersistentList<MainNavTab>,
    currentTab: MainNavTab?,
    onTabSelected: (MainNavTab) -> Unit,
) {
    var bottomBarHeight by remember { mutableIntStateOf(0) }

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        StaticAnimationWrapper(visible = visible) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .navigationPadding(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                ChangeBottomBarColor(Sub4_80)
                Box(
                    modifier = modifier
                        .fillMaxWidth(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_main_bottombar),
                        contentDescription = "Bottom bar background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .onGloballyPositioned { layoutCoordinates ->
                                bottomBarHeight = layoutCoordinates.size.height
                            },
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.Bottom
                ) {
                    tabs.forEach { tab ->
                        MainBottomBarItem(
                            tab = tab,
                            selected = tab == currentTab,
                            ordinal = tab.ordinal,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Main1,
                                unselectedIconColor = BottomBarInactive,
                            ),
                            onClick = { onTabSelected(tab) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainNavTab,
    selected: Boolean,
    ordinal: Int,
    colors: NavigationBarItemColors,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .selectable(
                selected = selected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            ),
        horizontalArrangement = Arrangement.Center,
    ) {
        when (ordinal) {
            HOME_TAB -> {
                Icon(
                    painter = painterResource(tab.iconResId),
                    contentDescription = tab.contentDescription,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .aspectRatio(3f)
                        .padding(start = 30.dp),
                    tint = if (selected) colors.selectedIconColor else colors.unselectedIconColor,
                )
            }

            EXPLORE_TAB -> {
                Icon(
                    painter = painterResource(tab.iconResId),
                    contentDescription = tab.contentDescription,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(bottom = 54.dp),
                    tint = Color.Unspecified,
                )
            }

            MY_PAGE_TAB -> {
                Icon(
                    painter = painterResource(tab.iconResId),
                    contentDescription = tab.contentDescription,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .aspectRatio(3f)
                        .padding(end = 30.dp),
                    tint = if (selected) colors.selectedIconColor else colors.unselectedIconColor,
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    OffroadTheme {
        MainBottomBar(
            visible = true,
            tabs = MainNavTab.entries.toPersistentList(),
            currentTab = MainNavTab.HOME,
            onTabSelected = { },
        )
    }
}

private const val HOME_TAB = 0
private const val EXPLORE_TAB = 1
private const val MY_PAGE_TAB = 2
