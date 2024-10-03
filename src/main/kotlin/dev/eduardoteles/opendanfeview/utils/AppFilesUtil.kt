package dev.eduardoteles.opendanfeview.utils

import com.formdev.flatlaf.util.SystemInfo


class AppFilesUtil {
    companion object {
        fun getApplicationDirectory(): String {
            val baseAppDataDir = when {
                SystemInfo.isWindows -> System.getenv("APPDATA")
                SystemInfo.isMacOS -> System.getProperty("user.home") + "/Library/Application Support"
                SystemInfo.isLinux -> System.getProperty("user.home") + "/.local/share"
                else -> throw IllegalStateException("Unsupported OS")
            }


            val appDir = "$baseAppDataDir/OpenDanfeView"

            if (!java.io.File(appDir).exists()) {
                java.io.File(appDir).mkdirs()
            }

            return appDir
        }

        fun getCefDirectory(): String {
            val appDir = getApplicationDirectory()
            return "$appDir/cef"
        }

        fun getCefCacheDirectory(): String {
            val appDir = getApplicationDirectory()
            return "$appDir/cef_cache"
        }
    }
}
