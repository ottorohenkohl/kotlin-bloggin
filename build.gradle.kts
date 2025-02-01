import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("kapt") version "2.1.0"
    kotlin("plugin.allopen") version "2.0.21"
    kotlin("plugin.noarg") version "2.1.0"

    id("io.quarkus") version "3.17.2"
    id("org.kordamp.gradle.jandex") version "2.1.0"
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("org.jetbrains.kotlin.plugin.allopen")
        plugin("org.jetbrains.kotlin.plugin.noarg")

        plugin("io.quarkus")
        plugin("org.kordamp.gradle.jandex")
    }

    dependencies {
        implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:3.17.2"))

        kapt("org.hibernate.orm:hibernate-jpamodelgen:6.6.3.Final")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
        implementation("io.quarkus:quarkus-arc")
        implementation("io.quarkus:quarkus-container-image-docker")
        implementation("io.quarkus:quarkus-hibernate-orm")
        implementation("io.quarkus:quarkus-hibernate-validator")
        implementation("io.quarkus:quarkus-jdbc-postgresql")
        implementation("io.quarkus:quarkus-kotlin")
        implementation("io.quarkus:quarkus-oidc")
        implementation("io.quarkus:quarkus-rest")
        implementation("io.quarkus:quarkus-rest-jackson")
        implementation("io.quarkus:quarkus-smallrye-openapi")
        implementation("io.quarkus:quarkus-smallrye-jwt")
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }

    repositories {
        mavenCentral()
        mavenLocal()
    }

    allOpen {
        annotation("jakarta.enterprise.context.ApplicationScoped")
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.ws.rs.Path")
    }

    noArg {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.Embeddable")
        annotation("jakarta.persistence.MappedSuperclass")
    }
}

group = "dev.rohenkohl"
version = "1.0"