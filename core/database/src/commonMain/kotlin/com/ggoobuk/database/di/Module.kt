package com.ggoobuk.database.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ggoobuk.database.GgoobukDatabase
import com.ggoobuk.database.dao.BookmarkDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val coreDatabaseModule = module {
    single<GgoobukDatabase> {
        val builder: RoomDatabase.Builder<GgoobukDatabase> = get()
        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single<BookmarkDao> { get<GgoobukDatabase>().bookmarkDao() }
}