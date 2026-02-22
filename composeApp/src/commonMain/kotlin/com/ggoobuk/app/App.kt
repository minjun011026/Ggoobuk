package com.ggoobuk.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.ggoobuk.app.navigation.AlarmRoute
import com.ggoobuk.app.navigation.BusRunningRoute
import com.ggoobuk.app.navigation.BusSetupRoute
import com.ggoobuk.app.navigation.HomeRoute
import com.ggoobuk.app.navigation.SubwayRunningRoute
import com.ggoobuk.app.navigation.SubwaySetupRoute
import com.ggoobuk.app.navigation.TimerRunningRoute
import com.ggoobuk.app.navigation.TimerSetupRoute
import com.ggoobuk.app.navigation.navigationConfig
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@Composable
fun App() {
    MaterialTheme {
        // KMP 타겟을 위한 SavedStateConfiguration을 전달하여 백스택 생성
        val backStack = rememberNavBackStack(navigationConfig, HomeRoute)

        NavDisplay(
            backStack = backStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator() // NavEntry 범위의 ViewModel 지원
            ),
            entryProvider = entryProvider {
                entry<HomeRoute> {

                }

                entry<TimerSetupRoute> {

                }

                entry<BusSetupRoute> {

                }

                entry<SubwaySetupRoute> {

                }

                entry<TimerRunningRoute> {

                }

                entry<BusRunningRoute> {

                }

                entry<SubwayRunningRoute> {

                }

                entry<AlarmRoute> {

                }
            }
        )
    }
}

internal val appModule = module {
}

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) {
    startKoin {
        appDeclaration()
        modules(appModule)
    }
}