package com.minjun.convention.extensions

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog

internal fun VersionCatalog.version(name: String): String = findVersion(name).get().requiredVersion

internal fun VersionCatalog.library(name: String): MinimalExternalModuleDependency = findLibrary(name).get().get()
