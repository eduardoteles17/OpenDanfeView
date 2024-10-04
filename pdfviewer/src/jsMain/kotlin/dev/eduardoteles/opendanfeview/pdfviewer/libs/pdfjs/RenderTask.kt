package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

import kotlin.js.Promise

external object RenderTask {
    fun onContinue()
    val promise: Promise<Unit>
    fun cancel(extraDelay: Int = definedExternally)
}
