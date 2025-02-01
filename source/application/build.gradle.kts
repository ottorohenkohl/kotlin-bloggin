dependencies {
    implementation(project(":component"))
    implementation(project(":configuration"))
    implementation(project(":person"))
    implementation(project(":zero"))
}

tasks.quarkusDependenciesBuild {
    dependsOn("jandex")
}