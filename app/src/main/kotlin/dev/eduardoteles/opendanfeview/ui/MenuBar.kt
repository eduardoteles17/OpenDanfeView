package dev.eduardoteles.opendanfeview.ui

import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class MenuBar(
    openDanfe: () -> Unit
) : JMenuBar() {
    private val fileMenu = JMenu("Arquivo").apply {
        val openItem = JMenuItem("Abrir Danfe")
        openItem.addActionListener {
            openDanfe()
        }

        add(openItem)
    }

    private val helpMenu = JMenu("Ajuda").apply {
        val aboutItem = JMenuItem("Sobre")
        aboutItem.addActionListener {
            val aboutDialog = AboutDialog()
            aboutDialog.isVisible = true
        }

        add(aboutItem)
    }

    init {
        add(fileMenu)
        add(helpMenu)
    }
}
