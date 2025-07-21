plugins {
    kotlin("jvm") version "2.2.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "13.0.0" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set("1.3.1")
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        ignoreFailures.set(false)
        filter {
            exclude("**/build/**")
        }
    }
}