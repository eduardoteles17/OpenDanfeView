package dev.eduardoteles.opendanfeview.ui

import org.cef.CefClient
import java.awt.Component
import javax.swing.JPanel

class Browser(
    private val cefClient: CefClient, private val url: String
) : JPanel() {
    val browserUI: Component?
    val browser = cefClient.createBrowser(url, false, false)

    init {
        browserUI = browser.uiComponent

        add(browser.uiComponent)
    }
}
