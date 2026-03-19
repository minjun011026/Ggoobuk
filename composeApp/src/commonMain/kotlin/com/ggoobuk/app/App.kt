package com.ggoobuk.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.ggoobuk.app.component.GgoobukNavigationScaffold
import com.ggoobuk.app.component.MainScreenTab
import com.ggoobuk.app.navigation.AlarmRoute
import com.ggoobuk.app.navigation.BusRunningRoute
import com.ggoobuk.app.navigation.BusSetupRoute
import com.ggoobuk.app.navigation.SubwayRunningRoute
import com.ggoobuk.app.navigation.SubwaySetupRoute
import com.ggoobuk.app.navigation.TimerRunningRoute
import com.ggoobuk.app.navigation.TimerSetupRoute
import com.ggoobuk.app.navigation.navigationConfig
import com.ggoobuk.designsystem.theme.GgoobukTheme
import com.ggoobuk.timersetup.TimeSetupScreen
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module

@Composable
fun App() {
    GgoobukTheme {
        val backStack = rememberNavBackStack(navigationConfig, TimerSetupRoute)

        GgoobukNavigationScaffold(
            currentTab = backStack.lastOrNull()?.let {
                when(it) {
                    is TimerSetupRoute -> MainScreenTab.Timer
                    is BusSetupRoute -> MainScreenTab.Bus
                    is SubwaySetupRoute -> MainScreenTab.Subway
                    else -> null
                }
            },
            onTabSelected = {
                val navKey = when(it) {
                    MainScreenTab.Timer -> TimerSetupRoute
                    MainScreenTab.Bus -> BusSetupRoute
                    MainScreenTab.Subway -> SubwaySetupRoute
                }
                backStack.clear()
                backStack.add(navKey)
            }
        ) {
            NavDisplay(
                backStack = backStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator() // NavEntry 범위의 ViewModel 지원
                ),
                entryProvider = entryProvider {
                    entry<TimerSetupRoute> {
                        TimeSetupScreen()
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
}

internal val appModule = module {
    includes()
}

fun initKoin(
    configuration: KoinAppDeclaration? = null
) {
    startKoin {
        includes(configuration)
        modules(appModule)
    }
}