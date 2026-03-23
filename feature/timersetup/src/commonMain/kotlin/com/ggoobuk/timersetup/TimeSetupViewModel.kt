package com.ggoobuk.timersetup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class TimeSetupViewModel(

) : ViewModel() {
    val uiState = MutableStateFlow<TimeSetupUiState>(TimeSetupUiState.TimeSetup(
        hour = 0,
        minute = 0,
        second = 0,
        bookmarkedTimes = emptyList()
    ))
}