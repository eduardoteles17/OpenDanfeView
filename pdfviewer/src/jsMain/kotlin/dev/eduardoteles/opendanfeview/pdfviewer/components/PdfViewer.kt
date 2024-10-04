package dev.eduardoteles.opendanfeview.pdfviewer.components

import dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs.GetViewportParameters
import dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs.PDFJs
import dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs.RenderParameters
import io.kvision.core.Container
import io.kvision.html.Canvas
import io.kvision.panel.SimplePanel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch


class PdfViewer(val pdfUrl: String) : SimplePanel() {
    init {
        GlobalScope.launch {
            loadPdf()
        }
    }

    private suspend fun loadPdf() {
        val pdf = PDFJs.getDocument(pdfUrl).promise.await()
        for (i in 0 until pdf.numPages) {
            val pageNumber = i + 1
            pdf.getPage(pageNumber).then { page ->
                val viewport = page.getViewport(object : GetViewportParameters {
                    override var scale: Number = 1
                    override val rotation = null
                    override val offsetX = null
                    override val offsetY = null
                    override val dontFlip = null
                })

                val canvas = Canvas(
                    canvasWidth = viewport.width.toInt(),
                    canvasHeight = viewport.height.toInt(),
                )
                add(canvas)
                val ctx = canvas.context2D

                page.render(object : RenderParameters {
                    override val canvasContext = ctx
                    override val viewport = viewport
                    override val intent = null
                    override val annotationMode = null
                    override val transform = null
                    override val background = null
                    override val pageColors = null
                    override val isEditing = null
                })
            }
        }
    }
}
