package dev.eduardoteles.opendanfeview.ui

import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import javax.swing.*
import javax.swing.border.EmptyBorder

class AboutDialog : JDialog() {
    init {
        title = "Sobre"
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setSize(400, 300)
        setLocationRelativeTo(null)
        isModal = true

        val panel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            border = EmptyBorder(10, 10, 10, 10)

            val label = JLabel("OpenDanfeView").apply {
                font = font.deriveFont(Font.BOLD, 20f)
                alignmentX = Component.CENTER_ALIGNMENT
            }
            add(label)

            add(Box.createRigidArea(Dimension(0, 10)))

            val text = """
                Desenvolvido por Eduardo Teles
                """.trimIndent()
            val textArea = JTextArea(text).apply {
                isEditable = false
                lineWrap = true
                wrapStyleWord = true
                border = EmptyBorder(10, 10, 10, 10)
            }
            add(textArea)
        }

        val closeButton = JButton("Fechar").apply {
            addActionListener {
                dispose()
            }
        }

        add(panel, BorderLayout.CENTER)
        add(closeButton, BorderLayout.SOUTH)
    }
}
