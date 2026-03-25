package com.ggoobuk.data.repository.api

import com.ggoobuk.model.Time
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarkedTimes(): Flow<List<Time>>
    suspend fun addBookmark(time: Time)
    suspend fun removeBookmark(time: Time)
}