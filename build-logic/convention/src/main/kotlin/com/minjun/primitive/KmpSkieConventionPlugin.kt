package com.minjun.primitive

import co.touchlab.skie.configuration.FlowInterop
import co.touchlab.skie.configuration.SealedInterop
import co.touchlab.skie.configuration.SuspendInterop
import co.touchlab.skie.plugin.configuration.SkieExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KmpSkieConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply("co.touchlab.skie")
            }

            extensions.configure<SkieExtension> {
                features {
                    group {
                        coroutinesInterop.set(true)
                        SuspendInterop.Enabled(true)
                        FlowInterop.Enabled(true)
                        SealedInterop.Enabled(true)
                    }
                }
            }
        }
    }
}
