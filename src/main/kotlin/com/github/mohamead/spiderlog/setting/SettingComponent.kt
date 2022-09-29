package com.github.mohamead.spiderlog.setting

import com.github.mohamead.spiderlog.enum.FontStyle
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import java.awt.GraphicsEnvironment
import javax.swing.JPanel


internal class SettingComponent {

    private val mainPanel: JPanel
    private val cBoxFontName = ComboBox<String>()
    private val cBoxFontStyle = ComboBox<String>()
    private val cBoxFontSize = ComboBox<Int>()

    init {
        buildComboBoxes()
        mainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Font  : "), cBoxFontName, 1, false)
            .addLabeledComponent(JBLabel("Style : "), cBoxFontStyle, 1, false)
            .addLabeledComponent(JBLabel("Size  : "), cBoxFontSize, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    private fun buildComboBoxes() {
        //Font
        for (fontName in GraphicsEnvironment.getLocalGraphicsEnvironment().availableFontFamilyNames) {
            cBoxFontName.addItem(fontName)
        }
        //Style
        for (fontStyle in FontStyle.values()) {
            cBoxFontStyle.addItem(fontStyle.name)
        }
        //Size
        for (i in 8..26) {
            cBoxFontSize.addItem(i)
        }
    }

    fun getPanel(): JPanel {
        return mainPanel
    }

    fun getFontName(): String {
        return cBoxFontName.model.selectedItem.toString()
    }

    fun setFontName(fontName: String) {
        cBoxFontName.model.selectedItem = fontName
    }

    fun getFontStyle(): FontStyle {
        return FontStyle.findByIndex(cBoxFontStyle.selectedIndex)
    }

    fun setFontStyle(fontStyle: FontStyle) {
        cBoxFontStyle.selectedIndex = fontStyle.index
    }

    fun getFontSize(): Int {
        return cBoxFontSize.selectedItem as Int
    }

    fun setFontSize(newSize: Int) {
        cBoxFontSize.selectedItem = newSize
    }

}
