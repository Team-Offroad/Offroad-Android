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
    ":core:designsystem",
    ":core:navigation",
    ":core:datastore",
)

include(
    ":feature:home",
    ":feature:main",
    ":feature:auth",
    ":feature:explore",
    ":feature:mypage",
)