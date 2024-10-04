package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

external object OutlineNode {
    val title: String
    val bold: String
    val italic: String
    val color: String
    val dest: String?
    val url: String?
    val unsafeUrl: String?
    val newWindow: String?
    val count: Number?
    val items: Array<dynamic>
}
