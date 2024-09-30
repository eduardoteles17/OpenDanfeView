plugins {
    application
    kotlin("jvm") version "2.0.20"
}

group = "dev.eduardoteles"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "dev.eduardoteles.opendanfeview.OpenDanfeViewKt"
}
