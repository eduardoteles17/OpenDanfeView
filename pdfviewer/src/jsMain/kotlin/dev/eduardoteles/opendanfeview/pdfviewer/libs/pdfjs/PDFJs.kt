package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

@JsModule("pdfjs-dist/build/pdf.mjs")
@JsNonModule
external object PDFJs {
    val GlobalWorkerOptions: GlobalWorkerOptions
}
