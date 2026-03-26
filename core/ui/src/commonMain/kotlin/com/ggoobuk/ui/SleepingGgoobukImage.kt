package com.ggoobuk.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ggoobuk.core.ui.generated.resources.Res
import ggoobuk.core.ui.generated.resources.img_ggoobuk_sleeping
import org.jetbrains.compose.resources.painterResource

@Composable
fun SleepingGgoobukImage(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(Res.drawable.img_ggoobuk_sleeping),
        contentDescription = "조는 중인 꾸벅이",
        modifier = modifier
    )
}


@Preview
@Composable
private fun SleepingGgoobukImagePreview() {
    SleepingGgoobukImage()
}