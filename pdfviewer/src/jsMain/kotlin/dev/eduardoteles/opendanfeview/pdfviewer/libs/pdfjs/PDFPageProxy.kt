package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

import kotlin.js.Promise

external class PDFPageProxy {
    val destroyed: Boolean
    val pageNumber: Int
    val rotate: Number
    val ref: dynamic
    val userUnit: Number
    val view: Array<Number>
    fun getViewport(parameters: GetViewportParameters): PageViewport
    fun getAnnotations(): Promise<Array<dynamic>>
    fun render(renderParameter: RenderParameters): RenderTask
}
