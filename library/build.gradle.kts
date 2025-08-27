plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "com.github.kaleidot725"
version = "1.6.0"

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            groupId = "com.github.kaleidot725"
            artifactId = "scrcpykt"

            pom {
                name.set("ScrcpyKt")
                description.set("A Kotlin client library for scrcpy (Android screen mirroring)")
                url.set("https://github.com/kaleidot725/ScrcpyKt")

                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }

                developers {
                    developer {
                        id.set("kaleidot725")
                        name.set("kaleidot725")
                    }
                }
            }
        }
    }
}
