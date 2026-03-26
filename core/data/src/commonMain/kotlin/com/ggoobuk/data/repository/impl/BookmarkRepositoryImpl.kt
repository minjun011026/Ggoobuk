package com.ggoobuk.data.repository.impl

import com.ggoobuk.data.repository.api.BookmarkRepository
import com.ggoobuk.database.dao.BookmarkDao
import com.ggoobuk.database.dto.toDomain
import com.ggoobuk.database.dto.toEntity
import com.ggoobuk.model.Time
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class BookmarkRepositoryImpl(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {

    override fun getBookmarkedTimes(): Flow<List<Time>> {
        return bookmarkDao.getBookmarks().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addBookmark(time: Time) {
        bookmarkDao.insertBookmark(time.toEntity())
    }

    override suspend fun removeBookmark(time: Time) {
        bookmarkDao.deleteBookmarkByTime(
            hour = time.hour,
            minute = time.minute,
            second = time.second
        )
    }
}