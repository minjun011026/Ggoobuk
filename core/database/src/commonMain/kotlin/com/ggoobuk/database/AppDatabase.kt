package com.ggoobuk.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.ggoobuk.database.dao.BookmarkDao
import com.ggoobuk.database.dto.TimeEntity

@Database(
    entities = [TimeEntity::class],
    version = 1,
    exportSchema = false // KMP에서는 현재 스키마 익스포트 지원이 제한적일 수 있음
)
@ConstructedBy(GgoobukDatabaseConstructor::class)
abstract class GgoobukDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}

@Suppress("KotlinNoActualForExpect")
expect object GgoobukDatabaseConstructor : RoomDatabaseConstructor<GgoobukDatabase> {
    override fun initialize(): GgoobukDatabase
}