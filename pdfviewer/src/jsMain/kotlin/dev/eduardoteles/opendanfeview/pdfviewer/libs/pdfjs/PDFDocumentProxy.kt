package dev.eduardoteles.opendanfeview.pdfviewer.libs.pdfjs

import kotlin.js.Promise

external object PDFDocumentProxy {
    val annotationStorage: dynamic
    val filterFactory: dynamic
    val numPages: Int
    val fingerprints: Array<String>
    val isPureXfa: Boolean
    val allXfaHtml: dynamic
    fun getPage(pageNumber: Number): Promise<PDFPageProxy>
    fun getPageIndex(ref: dynamic): Promise<Number>
    fun getDestinations(): Promise<Map<String, Array<dynamic>>>
    fun getDestination(id: String): Promise<Array<dynamic>?>
    fun getPageLabels(): Promise<Array<String>?>
    fun getPageLayout(): Promise<String>
    fun getPageMode(): Promise<String>
    fun getViewerPreferences(): Promise<dynamic>
    fun getOpenAction(): Promise<dynamic>
    fun getAttachments(): Promise<dynamic>
    fun getJSActions(): Promise<dynamic>
    fun getOutline(): Promise<Array<OutlineNode>>
    fun destroy(): Promise<Unit>
}
