plugins {
    idea
    application
    kotlin("jvm")
    id("com.gradleup.shadow") version "8.3.2"
}


fun fromProperties(key: String) = project.findProperty(key).toString()

group = fromProperties("group")
version = fromProperties("version")

repositories {
    mavenCentral()
    maven {
        url = uri("https://jaspersoft.jfrog.io/artifactory/jaspersoft-repo/")
    }
}

dependencies {
    implementation("br.com.swconsultoria:java-danfe:1.6")
    implementation("com.formdev:flatlaf:3.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines:0.19.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.9.0")
    implementation("me.friwi:jcefmaven:122.1.10")
    testImplementation(kotlin("test"))
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
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
