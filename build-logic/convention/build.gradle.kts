plugins {
    `kotlin-dsl`
}

group = "com.ggoobuk.convention"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.spotless.gradlePlugin)
    implementation(libs.skie.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kmpPrimitive") {
            id = "ggoobuk.primitive.kmp"
            implementationClass = "com.ggoobuk.primitive.KmpPrimitivePlugin"
        }
        register("kmpIosPrimitive") {
            id = "ggoobuk.primitive.kmp.ios"
            implementationClass = "com.ggoobuk.primitive.KmpIosPrimitivePlugin"
        }
        register("kmpComposePrimitive") {
            id = "ggoobuk.primitive.kmp.compose"
            implementationClass = "com.ggoobuk.primitive.KmpComposePrimitivePlugin"
        }
        register("composeResourcePrimitive") {
            id = "ggoobuk.primitive.compose.resources"
            implementationClass = "com.ggoobuk.primitive.ComposeResourceConventionPlugin"
        }
        register("kmpSkiePrimitive") {
            id = "ggoobuk.primitive.skie"
            implementationClass = "com.ggoobuk.primitive.KmpSkieConventionPlugin"
        }
        register("spotlessPrimitive") {
            id = "ggoobuk.primitive.spotless"
            implementationClass = "com.ggoobuk.primitive.SpotlessConventionPlugin"
        }
        register("koinPrimitive") {
            id = "ggoobuk.primitive.koin"
            implementationClass = "com.ggoobuk.primitive.KoinConventionPlugin"
        }
        register("kmpFeature") {
            id = "ggoobuk.convention.kmp.feature"
            implementationClass = "com.ggoobuk.convention.KmpFeatureConventionPlugin"
        }
    }
}
