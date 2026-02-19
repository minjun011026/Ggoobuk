package com.minjun.convention

import com.minjun.convention.extensions.library
import com.minjun.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("template.primitive.kmp")
                apply("template.primitive.kmp.ios")
                apply("template.primitive.kmp.compose")
                apply("template.primitive.compose.resources")
                apply("template.primitive.koin")
                apply("template.primitive.spotless")
                apply("template.primitive.skie")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.getByName("commonMain").dependencies {
                    implementation(libs.library("kotlinxCollectionsImmutable"))
                    implementation(libs.library("androidx-lifecycle-runtimeCompose"))
                    implementation(libs.library("androidx-lifecycle-viewmodelCompose"))
                }

                sourceSets.getByName("commonTest").dependencies {
                    implementation(libs.library("compose-ui-test"))
                    implementation(libs.library("kotlin-test"))
                }
            }
        }
    }
}
