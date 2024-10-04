plugins {
    kotlin("multiplatform")
    val kvisionVersion: String by System.getProperties()
    id("io.kvision") version kvisionVersion
}


repositories {
    mavenCentral()
}

val kvisionVersion: String by System.getProperties()

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("io.kvision:kvision:$kvisionVersion")
                implementation(npm("pdfjs-dist", "4.6.82"))
            }
        }
    }
}
