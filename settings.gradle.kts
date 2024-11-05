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
        maven {
            url = uri("https://repository.map.naver.com/archive/maven")
        }
    }
}

rootProject.name = "Offroad"
include(":app")

include(
    ":core:common",
    ":core:designsystem",
    ":core:navigation",
)

include(
    ":feature:home",
    ":feature:main",
)
include(":feature:mypage")
include(":feature:explore")
include(":feature:auth")
include(":feature:characterchat")
