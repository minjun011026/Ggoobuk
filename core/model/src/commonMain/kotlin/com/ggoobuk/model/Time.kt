package com.ggoobuk.model

import androidx.compose.runtime.Immutable

@Immutable
data class Time(
    val hour: Int,
    val minute: Int,
    val second: Int,
)
