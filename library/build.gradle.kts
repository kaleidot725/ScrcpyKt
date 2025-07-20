plugins {
    kotlin("jvm")
    `maven-publish`
}

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
            
            pom {
                name.set("ScrcpyKt")
                description.set("A Kotlin client library for scrcpy (Android screen mirroring)")
                url.set("https://github.com/kaleidot725/ScrcpyKt")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
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