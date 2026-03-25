package com.ggoobuk.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<GgoobukDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("ggoobuk_room.db")
    return Room.databaseBuilder<GgoobukDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}