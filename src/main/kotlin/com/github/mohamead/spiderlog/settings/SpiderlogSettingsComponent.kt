package com.github.mohamead.spiderlog.settings

import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel


internal class SpiderlogSettingsComponent {

    private var mainPanel: JPanel? = null
    private val name = ComboBox<String>()
    private val style = ComboBox<String>()
    private val size = JBTextField()

    init{
        buildComboBoxes()
        mainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Font  : "), name, 1, false)
            .addLabeledComponent(JBLabel("Style : "), style, 1, false)
            .addLabeledComponent(JBLabel("Size  : "), size, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    private fun buildComboBoxes() {
        //Font
        name.addItem("Consoles")
        name.addItem("Segoe UI")
        //Style
        style.addItem("PLAIN")
        style.addItem("BOLD")
        style.addItem("ITALIC")
    }

    fun getPanel(): JPanel? {
        return mainPanel
    }

    fun getName(): Int {
        return name.selectedIndex
    }

    fun setName(newName: Int) {
        name.selectedIndex = newName
    }

    fun getStyle(): Int {
        return style.selectedIndex
    }

    fun setStyle(newStyle: Int) {
        style.selectedIndex = newStyle
    }

    fun getSize(): Int {
        return size.text.toInt()
    }

    fun setSize(newSize: Int) {
        size.text = newSize.toString()
    }

}
