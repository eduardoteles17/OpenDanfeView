package dev.eduardoteles.opendanfeview.pdfviewer

import dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs.PDFJs
import io.kvision.Application
import io.kvision.html.button
import io.kvision.html.div
import io.kvision.module
import io.kvision.panel.root
import io.kvision.startApplication

class PdfViewer : Application() {
    override fun start() {
        root("kvapp") {
            div("Hello World!")
            button(text = "Click me!") {
                onClick {
                    js("alert('Clicked!')") as Unit
                }
            }
        }
    }
}

fun main() {
    PDFJs.GlobalWorkerOptions.workerSrc = "pdfviewer-pdf.worker.js"

    startApplication(::PdfViewer, module.hot)
}
