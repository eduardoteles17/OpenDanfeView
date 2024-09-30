plugins {
    idea
    application
    kotlin("jvm") version "2.0.20"
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

group = "dev.eduardoteles"
version = "0.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jaspersoft.jfrog.io/artifactory/jaspersoft-repo/")
    }
}

dependencies {
    implementation("br.com.swconsultoria:java-danfe:1.6")
    implementation("com.formdev:flatlaf:3.5.1")
    implementation("com.github.pcorless.icepdf:icepdf-viewer:7.2.1")
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
