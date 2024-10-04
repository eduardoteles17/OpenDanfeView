package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

external object PageViewport {
    val viewBox: Array<Number>
    val scale: Number
    val rotation: Number
    val offsetX: Number
    val offsetY: Number
    val transform: Array<Number>
    val width: Number
    val height: Number
    val ramDims: dynamic
    fun convertToViewportPoint(x: Number, y: Number): Array<dynamic>
    fun convertToViewportRectangle(rect: Array<Any>): Array<Any>
    fun convertToPdfPoint(x: Number, y: Number): Array<dynamic>
}
