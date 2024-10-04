package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

external interface GetViewportParameters {
    val scale: Number
    val rotation: Number?
    val offsetX: Number?
    val offsetY: Number?
    val dontFlip: Boolean?
}
