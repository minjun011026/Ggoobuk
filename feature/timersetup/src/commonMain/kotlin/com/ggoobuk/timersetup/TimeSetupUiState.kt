package com.ggoobuk.timersetup

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.ggoobuk.model.Time

@Stable
sealed interface TimeSetupUiState {

    @Immutable
    data object Loading : TimeSetupUiState

    @Immutable
    data class TimeSetup(
        val hour : Int,
        val minute : Int,
        val second : Int,
        val bookmarkedTimes : List<Time>,
        val isBookmarked : Boolean
    ) : TimeSetupUiState

}