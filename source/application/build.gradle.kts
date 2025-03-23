dependencies {
    implementation(project(":component"))
    implementation(project(":page"))
    implementation(project(":user"))
    implementation(project(":zero"))
}

tasks.quarkusDependenciesBuild {
    dependsOn("jandex")
}