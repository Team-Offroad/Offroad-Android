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
    ":core:database",
    ":core:datastore",
    ":core:network",
    ":core:designsystem",
    ":core:model",
    ":core:navigation",
    ":core:domain",
)

include(
    ":feature:home",
    ":feature:main",
)
include(":feature:mypage")
include(":feature:explore")
include(":feature:auth")
