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

include(":component")
project(":component").projectDir = file("source/component")

include(":page")
project(":page").projectDir = file("source/page")

include(":user")
project(":user").projectDir = file("source/user")

include(":zero")
project(":zero").projectDir = file("source/zero")