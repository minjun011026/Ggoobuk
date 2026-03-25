package com.ggoobuk.app

import android.app.Application
import androidx.room.RoomDatabase
import com.ggoobuk.database.GgoobukDatabase
import com.ggoobuk.database.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.module

class GgoobukApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            modules(
                module {
                    single<RoomDatabase.Builder<GgoobukDatabase>> {
                        getDatabaseBuilder(get())
                    }
                }
            )
            androidLogger()
            androidContext(this@GgoobukApplication)
        }
    }
}