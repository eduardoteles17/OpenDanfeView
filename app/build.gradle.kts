fun fromProperties(key: String) = project.findProperty(key).toString()

plugins {
    idea
    application
    kotlin("jvm")
    id("com.gradleup.shadow") version "8.3.2"
}

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
    val coroutinesVersion: String by System.getProperties()

    implementation("br.com.swconsultoria:java-danfe:1.6")
    implementation("com.formdev:flatlaf:3.5.1")
    implementation("ch.qos.logback:logback-classic:1.5.8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:$coroutinesVersion")
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

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "dev.eduardoteles.opendanfeview.OpenDanfeViewKt"
}

tasks.test {
    useJUnitPlatform()
}

val copyPdfViewerArtifacts by tasks.registering(Copy::class) {
    dependsOn(":pdfviewer:build")

    from("../pdfviewer/build/dist/js/productionExecutable")
    into(project.layout.buildDirectory.dir("resources/main/pdfviewer"))
}

tasks.getByName("processResources") {
    inputs.dir(copyPdfViewerArtifacts.map { it.outputs.files.files })
}
