package com.ggoobuk.timersetup

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ggoobuk.designsystem.theme.GgoobukTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TimeSetupScreen(
    onTimerStartClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TimeSetupViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TimeSetupScreen(
        uiState = uiState,
        modifier = modifier,
    )
}

@Composable
internal fun TimeSetupScreen(
    uiState: TimeSetupUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (uiState) {
            TimeSetupUiState.Loading -> LoadingState()
            is TimeSetupUiState.TimeSetup -> TimeSetupContent(
                hour = uiState.hour,
                minute = uiState.minute,
                second = uiState.second,
            )
        }
    }
}

@Composable
internal fun LoadingState(
    modifier: Modifier = Modifier,
) {
    Text("Loading")
}

@Composable
internal fun TimeSetupContent(
    hour: Int,
    minute: Int,
    second: Int,
    modifier: Modifier = Modifier,
) {

    var isExpanded by remember { mutableStateOf(false) }

    val shadowElevation by animateDpAsState(
        targetValue = if (isExpanded) 8.dp else 0.dp,
        animationSpec = if (isExpanded) {
            tween(durationMillis = 200, delayMillis = 300)
        } else {
            tween(durationMillis = 0)
        },
        label = "DelayedShadowElevation"
    )

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = isExpanded,
                contentAlignment = Alignment.Center,
                transitionSpec = {
                    (fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.9f))
                        .togetherWith(fadeOut(animationSpec = tween(200)))
                        .using(SizeTransform(clip = false))
                },
                label = "TimerExpandAnimation"
            ) { expanded ->
                if (!expanded) {
                    Text(
                        text = "$hour:$minute:$second",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 64.sp
                        ),
                        color = Color(0xFF333333),
                        modifier = Modifier.clickable(
                            interactionSource = null,
                            indication = null,
                            onClick = { isExpanded = true }
                        )
                    )
                } else {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight()
                            .shadow(
                                elevation = shadowElevation,
                                shape = RoundedCornerShape(24.dp)
                            ),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                PickerLabel("Hours")
                                PickerLabel("Minutes")
                                PickerLabel("Seconds")
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth().height(120.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "$hour",
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 64.sp
                                    ),
                                )
                                Text(
                                    text = "$minute",
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 64.sp
                                    ),
                                )
                                Text(
                                    text = "$second",
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 64.sp
                                    ),
                                )
                            }

                            Button(
                                onClick = { isExpanded = false },
                                modifier = Modifier.fillMaxWidth().height(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFF008080
                                    )
                                )
                            ) {
                                Text("완료", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
internal fun PickerLabel(label: String) {
    Text(text = label, fontSize = 14.sp, color = Color.Gray)
}

@Preview
@Composable
private fun TimeSetupScreenPreview() {
    GgoobukTheme {
        TimeSetupContent(
            hour = 0,
            minute = 0,
            second = 0,
        )
    }
}