package com.ggoobuk.app.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ggoobuk.ui.LocalBottomNavigationBarsPadding

@Composable
internal fun GgoobukNavigationScaffold(
    currentTab: MainScreenTab?,
    onTabSelected: (MainScreenTab) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val animatedSelectedTabIndex by animateFloatAsState(
        targetValue = currentTab?.ordinal?.toFloat() ?: 0f,
        label = "animatedSelectedTabIndex",
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioLowBouncy,
        ),
    )
    val animatedColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.primaryFixed,
        label = "animatedColor",
        animationSpec = spring(stiffness = Spring.StiffnessLow),
    )

    GgoobukNavigationScaffold(
        currentTab = currentTab,
        onTabSelected = onTabSelected,
        animatedSelectedTabIndex = animatedSelectedTabIndex,
        animatedColor = animatedColor,
        modifier = modifier,
        content = content,
    )
}

@Composable
private fun GgoobukNavigationScaffold(
    currentTab: MainScreenTab?,
    onTabSelected: (MainScreenTab) -> Unit,
    animatedSelectedTabIndex: Float,
    animatedColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            AnimatedVisibility(currentTab != null) {
                GgoobukNavigationBar(
                    currentTab = currentTab ?: MainScreenTab.Timer,
                    onTabSelected = onTabSelected,
                    modifier = Modifier
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing
                                .exclude(WindowInsets.ime)
                                .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
                        ),
                )
            }
        },
        modifier = modifier,
    ) { bottomNavigationBarsPadding ->
        CompositionLocalProvider(LocalBottomNavigationBarsPadding provides bottomNavigationBarsPadding) {
            content()
        }
    }
}