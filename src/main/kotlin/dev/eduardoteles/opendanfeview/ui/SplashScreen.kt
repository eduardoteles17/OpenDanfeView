package dev.eduardoteles.opendanfeview.ui

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JLabel

class SplashScreen : JFrame("Splash Screen") {
    init {
        setSize(800, 600)
        contentPane.add(JLabel("Loading..."), BorderLayout.CENTER)
        isVisible = true
    }

    fun close() {
        isVisible = false
        dispose()
    }
}
