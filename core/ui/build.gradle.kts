plugins {
    alias(libs.plugins.ggoobuk.primitive.kmp)
    alias(libs.plugins.ggoobuk.primitive.kmp.ios)
    alias(libs.plugins.ggoobuk.primitive.kmp.compose)
    alias(libs.plugins.ggoobuk.primitive.spotless)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.material.icons.extended)
        }
    }
}