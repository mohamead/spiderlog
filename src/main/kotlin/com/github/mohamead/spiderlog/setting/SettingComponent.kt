package com.github.mohamead.spiderlog.setting

import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel


internal class SettingComponent {

    private var mainPanel: JPanel? = null
    private val cBoxName = ComboBox<String>()
    private val cBoxStyle = ComboBox<String>()
    private val cBoxSize = ComboBox<Int>()

    init{
        buildComboBoxes()
        mainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Font  : "), cBoxName, 1, false)
            .addLabeledComponent(JBLabel("Style : "), cBoxStyle, 1, false)
            .addLabeledComponent(JBLabel("Size  : "), cBoxSize, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    private fun buildComboBoxes() {
        //Font
        cBoxName.addItem("Consoles")
        cBoxName.addItem("Segoe UI")
        //Style
        cBoxStyle.addItem("PLAIN")
        cBoxStyle.addItem("BOLD")
        cBoxStyle.addItem("ITALIC")
        //Size
        for (i in 8..26) {
            cBoxSize.addItem(i)
        }
    }

    fun getPanel(): JPanel? {
        return mainPanel
    }

    fun getName(): Int {
        return cBoxName.selectedIndex
    }

    fun setName(newName: Int) {
        cBoxName.selectedIndex = newName
    }

    fun getStyle(): Int {
        return cBoxStyle.selectedIndex
    }

    fun setStyle(newStyle: Int) {
        cBoxStyle.selectedIndex = newStyle
    }

    fun getSize(): Int {
        return cBoxSize.selectedItem as Int
    }

    fun setSize(newSize: Int) {
        cBoxSize.selectedItem = newSize
    }

}
