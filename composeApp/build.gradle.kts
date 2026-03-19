plugins {
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ggoobuk.primitive.kmp)
    alias(libs.plugins.ggoobuk.primitive.kmp.ios)
    alias(libs.plugins.ggoobuk.primitive.skie)
    alias(libs.plugins.ggoobuk.primitive.koin)
    alias(libs.plugins.ggoobuk.primitive.kmp.compose)
    alias(libs.plugins.ggoobuk.primitive.compose.resources)
    alias(libs.plugins.ggoobuk.primitive.spotless)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.jetbrains.lifecycle.viewmodelNavigation3)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.material.icons.extended)

            implementation(projects.core.designsystem)
            implementation(projects.core.ui)
            implementation(projects.feature.timersetup)
            implementation(projects.feature.bussetup)
            implementation(projects.feature.subwaysetup)
            implementation(projects.feature.timerrunning)
            implementation(projects.feature.busrunning)
            implementation(projects.feature.subwayrunning)
            implementation(projects.feature.alarm)
        }
    }
}
