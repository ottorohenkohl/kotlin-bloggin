pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}

rootProject.name = "bloggin"

include(":application")
project(":application").projectDir = file("source/application")

include(":person")
project(":person").projectDir = file("source/person")

include(":component")
project(":component").projectDir = file("source/component")

include(":settings")
project(":settings").projectDir = file("source/settings")

include(":zero")
project(":zero").projectDir = file("source/zero")