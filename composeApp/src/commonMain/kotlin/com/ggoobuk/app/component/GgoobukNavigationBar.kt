package com.ggoobuk.app.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ggoobuk.designsystem.theme.GgoobukTheme

@Composable
internal fun GgoobukNavigationBar(
    currentTab: MainScreenTab,
    onTabSelected: (MainScreenTab) -> Unit,
    modifier: Modifier = Modifier,
) {

    val navigationItemsContentPadding = PaddingValues(horizontal = 12.dp)

    Box(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
            )
            .fillMaxWidth()
            .height(64.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        BottomNavigationBarItems(
            currentTab = currentTab,
            onTabSelected = onTabSelected,
            modifier = Modifier.padding(navigationItemsContentPadding),
        )
    }
}

@Composable
private fun BottomNavigationBarItems(
    currentTab: MainScreenTab,
    onTabSelected: (MainScreenTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.selectableGroup(),
    ) {
        MainScreenTab.entries.forEach { tab ->
            NavigationTabItem(
                tab = tab,
                selected = currentTab == tab,
                onTabSelected = onTabSelected,
                modifier = Modifier.weight(1f).fillMaxHeight(),
            )
        }
    }
}

@Composable
private fun NavigationTabItem(
    tab: MainScreenTab,
    selected: Boolean,
    onTabSelected: (MainScreenTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scale by animateFloatAsState(
        targetValue = if (selected) 1f else .98f,
        visibilityThreshold = .000001f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy,
        ),
        label = "scale",
    )

    val indicatorShape = MaterialTheme.shapes.medium

    Box(
        modifier = modifier
            .padding(vertical = 6.dp, horizontal = 28.dp)
            .scale(scale)
            .shadow(
                elevation = if (selected) 3.dp else 0.dp,
                shape = indicatorShape,
                clip = false
            )
            .clip(indicatorShape)
            .background(
                if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
            )
            .clickable(
                interactionSource = null,
                onClick = { onTabSelected(tab) },
                indication = null,
            )
        ,
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = if (selected) tab.iconOn else tab.iconOff,
            contentDescription = "",
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Preview
@Composable
private fun GgoobukNavigationBarPreview() {
    GgoobukTheme {
        Box(
            Modifier.background(Color.White)
        ) {
            GgoobukNavigationBar(
                currentTab = MainScreenTab.Timer,
                onTabSelected = {},
            )
        }
    }
}