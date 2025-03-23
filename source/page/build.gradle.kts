dependencies {
    implementation(project(":component"))
    implementation(project(":user"))
    implementation(project(":zero"))
}

tasks.quarkusDependenciesBuild {
    dependsOn("jandex")
}