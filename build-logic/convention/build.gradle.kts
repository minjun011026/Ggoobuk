plugins {
    `kotlin-dsl`
}

group = "com.minjun.convention"

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
            id = "template.primitive.kmp"
            implementationClass = "com.minjun.primitive.KmpPrimitivePlugin"
        }
        register("kmpIosPrimitive") {
            id = "template.primitive.kmp.ios"
            implementationClass = "com.minjun.primitive.KmpIosPrimitivePlugin"
        }
        register("kmpComposePrimitive") {
            id = "template.primitive.kmp.compose"
            implementationClass = "com.minjun.primitive.KmpComposePrimitivePlugin"
        }
        register("composeResourcePrimitive") {
            id = "template.primitive.compose.resources"
            implementationClass = "com.minjun.primitive.ComposeResourceConventionPlugin"
        }
        register("kmpSkiePrimitive") {
            id = "template.primitive.skie"
            implementationClass = "com.minjun.primitive.KmpSkieConventionPlugin"
        }
        register("spotlessPrimitive") {
            id = "template.primitive.spotless"
            implementationClass = "com.minjun.primitive.SpotlessConventionPlugin"
        }
        register("koinPrimitive") {
            id = "template.primitive.koin"
            implementationClass = "com.minjun.primitive.KoinConventionPlugin"
        }
        register("kmpFeature") {
            id = "template.convention.kmp.feature"
            implementationClass = "com.minjun.convention.KmpFeatureConventionPlugin"
        }
    }
}
