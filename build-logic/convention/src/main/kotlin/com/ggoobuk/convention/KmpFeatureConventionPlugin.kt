package com.ggoobuk.convention

import com.ggoobuk.convention.extensions.library
import com.ggoobuk.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("ggoobuk.primitive.kmp")
                apply("ggoobuk.primitive.kmp.ios")
                apply("ggoobuk.primitive.kmp.compose")
                apply("ggoobuk.primitive.compose.resources")
                apply("ggoobuk.primitive.koin")
                apply("ggoobuk.primitive.spotless")
                apply("ggoobuk.primitive.skie")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.getByName("commonMain").dependencies {
                    implementation(libs.library("kotlinxCollectionsImmutable"))
                    implementation(libs.library("androidx-lifecycle-runtimeCompose"))
                    implementation(libs.library("androidx-lifecycle-viewmodelCompose"))
                    implementation(project(":core:designsystem"))
                    implementation(project(":core:model"))
                    implementation(project(":core:domain"))
                    implementation(project(":core:data"))
                    implementation(project(":core:ui"))
                }

                sourceSets.getByName("commonTest").dependencies {
                    implementation(libs.library("compose-ui-test"))
                    implementation(libs.library("kotlin-test"))
                }
            }
        }
    }
}
