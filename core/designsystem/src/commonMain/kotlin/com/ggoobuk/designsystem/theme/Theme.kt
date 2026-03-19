package com.ggoobuk.designsystem.theme

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightScheme = lightColorScheme(
    primary = ggoobukTeal,
    onPrimary = Color.White,
    primaryContainer = ggoobukTealLight,
    onPrimaryContainer = ggoobukTealDark,

    secondary = ggoobukCoral,
    onSecondary = Color.White,

    background = Color.White,
    onBackground = neutral10,

    surface = Color.White,
    onSurface = neutral10,
    surfaceVariant = neutral90,
    onSurfaceVariant = Color(0xFF3F4948),

    outline = Color(0xFF6F7979),

    error = Color(0xFFBA1A1A),
    onError = Color.White
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun GgoobukTheme(
    content: @Composable () -> Unit,
) {
    MaterialExpressiveTheme(
        colorScheme = lightScheme,
        content = content,
    )
}