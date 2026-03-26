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
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
import com.ggoobuk.model.Time
import com.ggoobuk.ui.BookmarkIconButton
import com.ggoobuk.ui.BookmarkItemCard
import com.ggoobuk.ui.GgoobukCustomChip
import com.ggoobuk.ui.SleepingGgoobukImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.koin.compose.viewmodel.koinViewModel

@Suppress("ParamsComparedByRef")
@Composable
fun TimeSetupScreen(
    onTimerStartClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TimeSetupViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TimeSetupScreen(
        uiState = uiState,
        onTimeChange = viewModel::updateTime,
        onAddTimeClick = viewModel::addTimeOffset,
        onBookmarkToggle = viewModel::toggleBookmark,
        modifier = modifier,
    )
}

@Composable
internal fun TimeSetupScreen(
    uiState: TimeSetupUiState,
    onTimeChange: (Int, Int, Int) -> Unit,
    onAddTimeClick: (Int) -> Unit,
    onBookmarkToggle: () -> Unit,
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
                bookmarkedTimes = uiState.bookmarkedTimes,
                isBookmarked = uiState.isBookmarked,
                onTimeChange = onTimeChange,
                onAddTimeClick = onAddTimeClick,
                onBookmarkToggle = onBookmarkToggle,
            )
        }
    }
}

@Composable
internal fun LoadingState(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        SleepingGgoobukImage()
        Text(
            text = "꾸벅이가 일어나는 중이에요...!",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
            )
        )
    }
}

@Composable
internal fun TimeSetupContent(
    hour: Int,
    minute: Int,
    second: Int,
    bookmarkedTimes: ImmutableList<Time>,
    isBookmarked: Boolean,
    onTimeChange: (Int, Int, Int) -> Unit,
    onAddTimeClick: (Int) -> Unit,
    onBookmarkToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
    ) {
        TimerPickerCard(
            hour = hour,
            minute = minute,
            second = second,
            isBookmarked = isBookmarked,
            onTimeChange = onTimeChange,
            onBookmarkToggle = onBookmarkToggle,
        )
        TimeOffsetLayout(onAddTimeClick = onAddTimeClick)
        BookmarkSection(
            bookmarkedTimes = bookmarkedTimes,
            onTimeChange = onTimeChange,
        )
    }
}

@Composable
internal fun TimerPickerCard(
    hour: Int,
    minute: Int,
    second: Int,
    isBookmarked: Boolean,
    onTimeChange: (Int, Int, Int) -> Unit,
    onBookmarkToggle: () -> Unit,
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

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        AnimatedContent(
            targetState = isExpanded,
            contentAlignment = Alignment.Center,
            transitionSpec = {
                (fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.7f))
                    .togetherWith(fadeOut(animationSpec = tween(200)))
                    .using(SizeTransform(clip = false))
            },
            label = "TimerExpandAnimation"
        ) { expanded ->
            if (!expanded) {
                Text(
                    text = "$hour : $minute : $second",
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
                    Box(modifier = Modifier.fillMaxWidth()) {
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
                                modifier = Modifier.fillMaxWidth().height(140.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                NumberPickerDial(
                                    value = hour,
                                    range = 0..23,
                                    onValueChange = { newHour ->
                                        onTimeChange(newHour, minute, second)
                                    },
                                )
                                NumberPickerDial(
                                    value = minute,
                                    range = 0..59,
                                    onValueChange = { newMinute ->
                                        onTimeChange(hour, newMinute, second)
                                    },
                                )
                                NumberPickerDial(
                                    value = second,
                                    range = 0..59,
                                    onValueChange = { newSecond ->
                                        onTimeChange(hour, minute, newSecond)
                                    },
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

                        BookmarkIconButton(
                            isBookmarked = isBookmarked,
                            onClick = onBookmarkToggle,
                            modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
                        )

                    }
                }
            }
        }
    }
}

@Composable
internal fun BookmarkSection(
    bookmarkedTimes: ImmutableList<Time>,
    onTimeChange: (Int, Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "즐겨찾기 목록",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF333333)
            )
        )

        if (bookmarkedTimes.isEmpty()) {
            Text(
                text = "저장된 시간이 없습니다.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 12.dp),
            ) {
                items(bookmarkedTimes) { time ->
                    key("${time.hour}-${time.minute}-${time.second}") {
                        BookmarkItemCard(
                            text = "${time.hour.toString().padStart(2, '0')} : " +
                                    "${time.minute.toString().padStart(2, '0')} : " +
                                    time.second.toString().padStart(2, '0'),
                            onClick = { onTimeChange(time.hour, time.minute, time.second) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun TimeOffsetLayout(
    onAddTimeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        val timeOffsets =
            listOf(1 to "+ 1분", 5 to "+ 5분", 10 to "+ 10분", 30 to "+ 30분", 60 to "+ 1시간")
        timeOffsets.forEach { (minutes, label) ->
            GgoobukCustomChip(
                onClick = { onAddTimeClick(minutes) },
                label = label
            )
        }
    }
}

@Composable
internal fun NumberPickerDial(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val itemHeight = 40.dp
    val containerHeight = 120.dp
    val verticalPadding = (containerHeight - itemHeight) / 2
    val rangeList = range.toList()
    val rangeSize = rangeList.size

    val initialIndex = remember {
        val middle = Int.MAX_VALUE / 2
        middle - (middle % rangeSize) + rangeList.indexOf(value).coerceAtLeast(0)
    }

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val centerIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val actualValue = rangeList[centerIndex % rangeSize]
            onValueChange(actualValue)
        }
    }

    LaunchedEffect(value) {
        val currentVisibleValue = rangeList[centerIndex % rangeSize]
        if (value != currentVisibleValue) {
            val targetIndex = centerIndex - (centerIndex % rangeSize) + rangeList.indexOf(value)
            listState.scrollToItem(targetIndex)
        }
    }

    LazyColumn(
        state = listState,
        flingBehavior = flingBehavior,
        modifier = modifier.width(60.dp).height(120.dp),
        contentPadding = PaddingValues(bottom = verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(Int.MAX_VALUE) { index ->
            val actualValue = rangeList[index % rangeSize]
            val isSelected = index == centerIndex
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = actualValue.toString().padStart(2, '0'),
                    fontSize = if (isSelected) 32.sp else 20.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.Black else Color.LightGray
                )
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
        Surface {
            TimeSetupContent(
                hour = 0,
                minute = 0,
                second = 0,
                isBookmarked = false,
                bookmarkedTimes = persistentListOf(
                    Time(1, 2, 3),
                    Time(4, 5, 6),
                ),
                onTimeChange = { _, _, _ -> },
                onAddTimeClick = {},
                onBookmarkToggle = {},
            )
        }
    }
}

@Preview
@Composable
private fun TimeSetupScreenLoadingPreview() {
    GgoobukTheme {
        Surface {
            LoadingState()
        }
    }
}