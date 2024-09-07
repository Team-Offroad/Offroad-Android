package com.teamoffroad.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamoffroad.core.designsystem.component.StaticAnimationWrapper
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.BottomBarInactive
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.main.MainNavTab
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
    ProvideWindowInsets {
        StaticAnimationWrapper(visible = visible) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .navigationPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp)
                        .background(Sub4)
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
    ConstraintLayout(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            )
    ) {
        val navBtn = createRef()
        when (ordinal) {
            HOME_TAB -> {
                Icon(
                    painter = painterResource(tab.iconResId),
                    contentDescription = tab.contentDescription,
                    modifier = Modifier
                        .size(50.dp)
                        .constrainAs(navBtn) {
                            start.linkTo(parent.start, margin = 48.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    tint = if (selected) colors.selectedIconColor else colors.unselectedIconColor,
                )
            }

            EXPLORE_TAB -> {
                Icon(
                    painter = painterResource(tab.iconResId),
                    contentDescription = tab.contentDescription,
                    modifier = Modifier
                        .size(78.dp)
                        .constrainAs(navBtn) {
                            bottom.linkTo(parent.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    tint = Color.Unspecified,
                )
            }

            MY_PAGE_TAB -> {
                Icon(
                    painter = painterResource(tab.iconResId),
                    contentDescription = tab.contentDescription,
                    modifier = Modifier
                        .size(50.dp)
                        .constrainAs(navBtn) {
                            end.linkTo(parent.end, margin = 48.dp)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
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
