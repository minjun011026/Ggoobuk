package com.ggoobuk.app.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
data object HomeRoute : NavKey

@Serializable
data object TimerSetupRoute : NavKey

@Serializable
data object BusSetupRoute : NavKey

@Serializable
data object SubwaySetupRoute : NavKey

@Serializable
data object TimerRunningRoute : NavKey

@Serializable
data object BusRunningRoute : NavKey

@Serializable
data object SubwayRunningRoute : NavKey

@Serializable
data object AlarmRoute : NavKey

// iOS 등 Non-JVM 플랫폼의 다형성 직렬화를 위한 설정 (Open Polymorphism)
val navigationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeRoute::class, HomeRoute.serializer())
            subclass(TimerSetupRoute::class, TimerSetupRoute.serializer())
            subclass(BusSetupRoute::class, BusSetupRoute.serializer())
            subclass(SubwaySetupRoute::class, SubwaySetupRoute.serializer())
            subclass(TimerRunningRoute::class, TimerRunningRoute.serializer())
            subclass(BusRunningRoute::class, BusRunningRoute.serializer())
            subclass(SubwayRunningRoute::class, SubwayRunningRoute.serializer())
            subclass(AlarmRoute::class, AlarmRoute.serializer())
        }
    }
}
