package com.minjun.primitive

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import com.minjun.convention.extensions.getDefaultPackageName
import com.minjun.convention.extensions.libs
import com.minjun.convention.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpPrimitivePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.kotlin.multiplatform.library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                jvm()
                jvmToolchain(17)

                compilerOptions {
                   freeCompilerArgs.addAll(
                        "-Xexpect-actual-classes",
                        "-opt-in=kotlin.time.ExperimentalTime",
                    )
                }

                (this as ExtensionAware).extensions.configure<KotlinMultiplatformAndroidLibraryExtension>("androidLibrary") {
                    compileSdk = libs.version("android-compileSdk").toInt()
                    minSdk = libs.version("android-minSdk").toInt()
                    namespace = getDefaultPackageName(project.name)
                }
            }

        }
    }
}
