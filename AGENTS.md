# Ggoobuk Project

Ggoobuk is a Kotlin MultiPlatform mobile application written in Kotlin. 
It provides sleep alarms for public transit users (Bus & Subway). It ensures users wake up before their stop using location-based triggers or timers.

## Architecture

This project is a modern Android application that follows the official architecture guidance from Google. It is a reactive, single-activity app that uses the following:

-   **UI:** Built entirely with Jetpack Compose, including Material 3 components and adaptive layouts for different screen sizes.
-   **State Management:** Unidirectional Data Flow (UDF) is implemented using Kotlin Coroutines and `Flow`s. `ViewModel`s act as state holders, exposing UI state as streams of data.
-   **Dependency Injection:** Koin is used for dependency injection throughout the app, simplifying the management of dependencies and improving testability.
-   **Navigation:** Navigation is handled by Navigation3 for Compose, allowing for a declarative and type-safe way to navigate between screens.
-   **Data:** The data layer is implemented using the repository pattern.
    -   **Local Data:** Room (KMP) and DataStore for local persistence.
    -   **Remote Data:** Ktor Client for network requests (if applicable).
-   **Background Processing:** WorkManager is used for deferrable background tasks.

## Modules

The main Android app lives in the `app/` folder. Feature modules live in `feature/` and core and shared modules in `core/`.
