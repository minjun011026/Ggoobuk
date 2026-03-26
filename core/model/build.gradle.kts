plugins {
    alias(libs.plugins.ggoobuk.primitive.kmp)
    alias(libs.plugins.ggoobuk.primitive.kmp.ios)
    alias(libs.plugins.ggoobuk.primitive.spotless)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
        }
    }
}