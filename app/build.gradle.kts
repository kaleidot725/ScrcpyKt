plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(project(":library"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("jp.kaleidot725.scrcpykt.MainKt")
}