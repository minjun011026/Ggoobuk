package com.ggoobuk.primitive

import com.ggoobuk.convention.extensions.library
import com.ggoobuk.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpComposePrimitivePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val kmpExtension = extensions.getByType<KotlinMultiplatformExtension>()

            kmpExtension.sourceSets.getByName("commonMain").dependencies {
                implementation(libs.library("compose-components-resources"))
                implementation(libs.library("compose-runtime"))
                implementation(libs.library("compose-foundation"))
                implementation(libs.library("compose-material3"))
                implementation(libs.library("compose-ui"))
                implementation(libs.library("compose-uiToolingPreview"))
            }

            dependencies.add("androidRuntimeClasspath", libs.library("compose-uiTooling"))
        }
    }
}
