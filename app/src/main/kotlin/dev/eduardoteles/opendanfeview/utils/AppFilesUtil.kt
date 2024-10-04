package dev.eduardoteles.opendanfeview.utils

import com.formdev.flatlaf.util.SystemInfo
import java.io.File


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
            val appDirFile = File(appDir)

            if (!appDirFile.exists()) {
                appDirFile.mkdirs()
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

        fun getDanfeTemporaryDirectory(): String {
            val appDir = getApplicationDirectory()
            val tmpFolder = "$appDir/danfe_temp"
            val tmpFolderFile = File(tmpFolder)

            if (!tmpFolderFile.exists()) {
                tmpFolderFile.mkdirs()
            }

            return tmpFolder
        }
    }
}
