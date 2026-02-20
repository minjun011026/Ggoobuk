package com.ggoobuk.primitive

import com.ggoobuk.convention.extensions.library
import com.ggoobuk.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val kmpExtension = extensions.findByType(KotlinMultiplatformExtension::class.java)
            if (kmpExtension != null) {
                kmpExtension.sourceSets.getByName("commonMain").dependencies {
                    implementation(libs.library("koin-core"))
                    implementation(libs.library("koin-compose"))
                    implementation(libs.library("koin-compose-viewmodel"))
                }
            } else {
                // Fallback for non-KMP modules if any
                dependencies {
                    add("implementation", libs.library("koin-core"))
                }
            }
        }
    }
}
