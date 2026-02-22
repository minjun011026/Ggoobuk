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
        }
    }
}
