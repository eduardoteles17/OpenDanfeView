package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

external interface RenderParameters {
    val canvasContext: Any
    val viewport: PageViewport
    val intent: String?
    val annotationMode: Int?
    val transform: Array<Any>?
    val background: String?
    val pageColors: dynamic?
    val isEditing: Boolean?
}
