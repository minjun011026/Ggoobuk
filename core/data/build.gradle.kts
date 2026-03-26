plugins {
    alias(libs.plugins.ggoobuk.primitive.kmp)
    alias(libs.plugins.ggoobuk.primitive.kmp.ios)
    alias(libs.plugins.ggoobuk.primitive.spotless)
    alias(libs.plugins.ggoobuk.primitive.koin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(projects.core.database)
        }
    }
}
