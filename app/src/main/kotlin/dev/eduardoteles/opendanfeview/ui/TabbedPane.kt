package dev.eduardoteles.opendanfeview.ui

import java.awt.Component
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTabbedPane

class TabbedPane : JTabbedPane() {
    override fun addTab(title: String?, component: Component?) {
        val label = JLabel(title)
        val closeButton = JButton("X")
        closeButton.addActionListener {
            val index = indexOfComponent(component)
            removeTabAt(index)
        }

        val tabComponent = JPanel()
        tabComponent.add(label)
        tabComponent.add(closeButton)

        super.addTab(title, component)
        setTabComponentAt(tabCount - 1, tabComponent)
    }
}
