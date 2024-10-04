package dev.eduardoteles.opendanfeview.pdfviewer

import dev.eduardoteles.opendanfeview.pdfviewer.components.PdfViewer
import dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs.PDFJs
import io.kvision.Application
import io.kvision.CoreModule
import io.kvision.html.h1
import io.kvision.module
import io.kvision.panel.root
import io.kvision.require
import io.kvision.startApplication
import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams


class App : Application() {
    init {
        require("css/tailwind.css")
    }

    override fun start() {
        val pdfUrl = URLSearchParams(window.location.search).get("pdfUrl")
        root("kvapp") {
            if (pdfUrl != null) {
                add(PdfViewer(pdfUrl))
            } else {
                h1("PDF Viewer")
            }

        }
    }
}

fun main() {
    PDFJs.GlobalWorkerOptions.workerSrc = "pdfviewer-pdf.worker.js"

    startApplication(::App, module.hot, CoreModule)
}
