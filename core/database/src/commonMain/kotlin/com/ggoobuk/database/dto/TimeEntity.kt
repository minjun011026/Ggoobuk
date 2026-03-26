package com.ggoobuk.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ggoobuk.model.Time

@Entity(tableName = "bookmarked_time")
data class TimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val hour: Int,
    val minute: Int,
    val second: Int
)

fun TimeEntity.toDomain(): Time {
    return Time(
        hour = hour,
        minute = minute,
        second = second
    )
}

fun Time.toEntity(): TimeEntity {
    return TimeEntity(
        hour = hour,
        minute = minute,
        second = second
    )
}