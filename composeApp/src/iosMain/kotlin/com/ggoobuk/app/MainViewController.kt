package com.ggoobuk.app

import androidx.compose.ui.window.ComposeUIViewController
import androidx.room.RoomDatabase
import com.ggoobuk.database.GgoobukDatabase
import com.ggoobuk.database.getDatabaseBuilder
import org.koin.dsl.module

fun MainViewController() = ComposeUIViewController { App() }

fun initKoinIOS() {
    initKoin {
        modules(
            module {
                single<RoomDatabase.Builder<GgoobukDatabase>> {
                    getDatabaseBuilder()
                }
            }
        )
    }
}