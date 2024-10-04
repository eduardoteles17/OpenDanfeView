package dev.eduardoteles.opendanfeview.pdfviewer

import dev.eduardoteles.opendanfeview.utils.AppFilesUtil
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.random.Random

class PdfViewerWebServer private constructor() {
    var port = Random.nextInt(10000, 65535)
        private set

    private val logger = LoggerFactory.getLogger(PdfViewerWebServer::class.java)

    private val server = embeddedServer(Netty, port = port) {
        routing {
            staticResources("/", "pdfviewer")
            staticFiles("/pdf-files", File(AppFilesUtil.getDanfeTemporaryDirectory()))
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
