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
    val ktorVersion = "2.3.12"
    val kotlinCoroutines = "1.9.0"

    implementation("br.com.swconsultoria:java-danfe:1.6")
    implementation("com.formdev:flatlaf:3.5.1")
    implementation("ch.qos.logback:logback-classic:1.5.8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:$kotlinCoroutines")
    implementation("me.friwi:jcefmaven:122.1.10")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
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
