package com.ggoobuk.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ggoobuk.database.dto.TimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarked_time ORDER BY id DESC")
    fun getBookmarks(): Flow<List<TimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(timeEntity: TimeEntity)

    @Query("DELETE FROM bookmarked_time WHERE hour = :hour AND minute = :minute AND second = :second")
    suspend fun deleteBookmarkByTime(hour: Int, minute: Int, second: Int)
}