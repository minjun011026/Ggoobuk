package com.minjun.primitive

import com.diffplug.gradle.spotless.SpotlessExtension
import com.minjun.convention.extensions.libs
import com.minjun.convention.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.diffplug.spotless")
            }

            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("src/**/*.kt")
                    ktlint(libs.version("ktlint"))
                }
            }
        }
    }
}
