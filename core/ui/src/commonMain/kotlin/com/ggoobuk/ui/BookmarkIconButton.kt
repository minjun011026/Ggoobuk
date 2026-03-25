package com.ggoobuk.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BookmarkIconButton(
    isBookmarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = if (isBookmarked) Icons.Filled.Star else Icons.Outlined.StarBorder,
            contentDescription = "Bookmark",
            tint = if (isBookmarked) Color(0xFFFFD700) else Color.Gray
        )
    }
}

@Preview
@Composable
private fun BookmarkIconButtonPreview() {
    BookmarkIconButton(isBookmarked = false, onClick = {})
}