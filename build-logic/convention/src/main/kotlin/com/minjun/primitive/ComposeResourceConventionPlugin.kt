package com.minjun.primitive

import com.minjun.convention.extensions.getDefaultPackageName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.resources.ResourcesExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinSingleTargetExtension

class ComposeResourceConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val configurationName = when {
                extensions.findByType(KotlinMultiplatformExtension::class.java) != null -> "commonMainImplementation"
                extensions.findByType(KotlinSingleTargetExtension::class.java) != null -> "implementation"
                else -> "implementation"
            }

            val compose = extensions.getByType<ComposeExtension>()

            dependencies.add(configurationName, compose.dependencies.components.resources)

            compose.extensions.configure<ResourcesExtension>("resources") {
                val namespace = getDefaultPackageName(project.name)
                packageOfResClass = namespace
                val className = namespace.split(".").last()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } + "Res"
                nameOfResClass = className
                generateResClass = ResourcesExtension.ResourceClassGeneration.Always
            }

        }
    }
}
