package com.ggoobuk.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GgoobukCustomChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .offset(x = 2.dp, y = 2.dp)
                .width(90.dp)
                .height(40.dp),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.primary,
        ) { }

        Surface(
            onClick = onClick,
            modifier = Modifier
                .width(90.dp)
                .height(40.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = label,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun GgoobukCustomChipPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        GgoobukCustomChip(
            label = "+ 1분",
            onClick = {}
        )
    }
}