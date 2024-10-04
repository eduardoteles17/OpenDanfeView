package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

import kotlin.js.Promise

external object PDFDocumentLoadingTask {
    val docId: String
    val destroyed: Boolean
    fun onPassword()
    fun onProgress()
    val promise: Promise<PDFDocumentProxy>
    fun destroy()
}
