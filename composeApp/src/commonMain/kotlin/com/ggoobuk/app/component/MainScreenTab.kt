package com.ggoobuk.app.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsSubway
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.DirectionsSubway
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.ui.graphics.vector.ImageVector
import com.ggoobuk.app.composeApp.ComposeAppRes
import com.ggoobuk.app.composeApp.bus
import com.ggoobuk.app.composeApp.subway
import com.ggoobuk.app.composeApp.timer
import org.jetbrains.compose.resources.StringResource

enum class MainScreenTab(
    val label: StringResource,
    val iconOn: ImageVector,
    val iconOff: ImageVector,
) {
    Timer(
        label = ComposeAppRes.string.timer,
        iconOn = Icons.Filled.Timer,
        iconOff = Icons.Outlined.Timer,
    ),
    Bus(
        label = ComposeAppRes.string.bus,
        iconOn = Icons.Filled.DirectionsBus,
        iconOff = Icons.Outlined.DirectionsBus,
    ),
    Subway(
        label = ComposeAppRes.string.subway,
        iconOn = Icons.Filled.DirectionsSubway,
        iconOff = Icons.Outlined.DirectionsSubway,
    ),
}