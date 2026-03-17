rootProject.name = "Ggoobuk"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {

    includeBuild("build-logic")

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":androidApp")
include(":core:domain")
include(":feature:timersetup")
include(":feature:bussetup")
include(":feature:subwaysetup")
include(":feature:timerrunning")
include(":feature:busrunning")
include(":feature:subwayrunning")
include(":feature:alarm")
