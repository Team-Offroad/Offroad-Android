pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Offroad"
include(":app")

include(
    ":core:common",
    ":core:data",
    ":core:datastore",
    ":core:designsystem",
    ":core:model",
    ":core:navigation",
    ":core:domain",
)

include(
    ":feature:home",
    ":feature:main",
)
