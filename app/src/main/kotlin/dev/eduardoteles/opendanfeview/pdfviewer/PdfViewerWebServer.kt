package dev.eduardoteles.opendanfeview.pdfviewer

import dev.eduardoteles.opendanfeview.utils.AppFolderUtils
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.io.File
import java.net.ServerSocket
import kotlin.random.Random

class PdfViewerWebServer private constructor() {
    var port = getValidRandomPort()
        private set

    private val logger = LoggerFactory.getLogger(PdfViewerWebServer::class.java)

    private fun getValidRandomPort(): Int {
        val newPort = Random.nextInt(10000, 65535)

        try {
            ServerSocket(newPort).use { serverlessSocket ->
                serverlessSocket.reuseAddress = true
            }

            return newPort
        } catch (e: Exception) {
            return getValidRandomPort()
        }
    }

    private val server = embeddedServer(Netty, host = "127.0.0.1", port = port) {
        routing {
            staticResources("/", "pdfviewer")
            staticFiles("/pdf-files", File(AppFolderUtils.getDanfeTemporaryDirectory()))
        }
    }

    fun start() {
        logger.info("Starting server on port $port")
        server.start(wait = false)
    }

    fun stop() {
        logger.info("Stopping server")
        server.stop(gracePeriodMillis = 3000, timeoutMillis = 5000)
    }

    companion object {
        val instance: PdfViewerWebServer by lazy { PdfViewerWebServer() }
    }
}
