package dev.eduardoteles.opendanfeview.pdfviewer

import io.kvision.Application
import io.kvision.html.div
import io.kvision.module
import io.kvision.panel.root
import io.kvision.startApplication

class PdfViewer : Application() {
    override fun start() {
        root("kvapp") {
            div("Hello World!")
        }
    }
}

fun main() {
    startApplication(::PdfViewer, module.hot)
}
