package com.minjun.convention.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.getDefaultPackageName(projectName: String): String {
    val basePackage = "com.example.kmp"
    val projectSuffix = projectName.replace("-", ".").replace(":", ".")
    return if (projectSuffix.isEmpty()) basePackage else "$basePackage.$projectSuffix"
}
