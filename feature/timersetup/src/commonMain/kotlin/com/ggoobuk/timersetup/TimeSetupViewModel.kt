package com.ggoobuk.timersetup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ggoobuk.data.repository.api.BookmarkRepository
import com.ggoobuk.domain.usecase.CheckBookmarkUseCase
import com.ggoobuk.model.Time
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TimeSetupViewModel(
    private val bookmarkRepository: BookmarkRepository,
    private val checkBookmarkUseCase: CheckBookmarkUseCase,
) : ViewModel() {

    private val _hour = MutableStateFlow(0)
    private val _minute = MutableStateFlow(0)
    private val _second = MutableStateFlow(0)

    private val _bookmarks: StateFlow<List<Time>> = bookmarkRepository.getBookmarkedTimes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList() // 초기값은 빈 리스트
        )

    val uiState: StateFlow<TimeSetupUiState> = combine(
        _hour, _minute, _second, _bookmarks
    ) { h, m, s, bookmarks ->
        TimeSetupUiState.TimeSetup(
            hour = h,
            minute = m,
            second = s,
            bookmarkedTimes = bookmarks,
            isBookmarked = checkBookmarkUseCase(h, m, s, bookmarks)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = TimeSetupUiState.Loading
    )

    fun updateHour(hour: Int) {
        _hour.value = hour.coerceIn(0, 99)
    }

    fun updateMinute(minute: Int) {
        _minute.value = minute.coerceIn(0, 59)
    }

    fun updateSecond(second: Int) {
        _second.value = second.coerceIn(0, 59)
    }

    fun addTimeOffset(minutesToAdd: Int) {
        val currentTotalMinutes = (_hour.value * 60) + _minute.value + minutesToAdd
        _hour.value = (currentTotalMinutes / 60).coerceIn(0, 99)
        _minute.value = currentTotalMinutes % 60
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            val h = _hour.value
            val m = _minute.value
            val s = _second.value
            val targetTime = Time(h, m, s)

            val isCurrentlyBookmarked = checkBookmarkUseCase(h, m, s, _bookmarks.value)

            if (isCurrentlyBookmarked) {
                bookmarkRepository.removeBookmark(targetTime)
            } else {
                bookmarkRepository.addBookmark(targetTime)
            }
        }
    }
}