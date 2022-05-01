package com.github.mohamead.spiderlog.setting

import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel


internal class SettingComponent {

    private val mainPanel: JPanel
    private val cBoxFontName = ComboBox<String>()
    private val cBoxFontStyle = ComboBox<String>()
    private val cBoxFontSize = ComboBox<Int>()

    init{
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
        for (fontName in FontName.values()) {
            cBoxFontName.addItem(fontName.value)
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

    fun getFontName(): FontName {
        return FontName.findByIndex(cBoxFontName.selectedIndex)
    }

    fun setFontName(fontName: FontName) {
        cBoxFontName.selectedIndex = fontName.index
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

internal enum class FontName(val value: String, val index: Int) {
    CONSOLAS("Consolas", 0),
    SEGOE_UI("Segoe UI", 1);

    companion object {

        internal fun findByIndex(index: Int): FontName {
            return values().firstOrNull { it.index == index }
                ?: throw NoSuchElementException("FontName with index '$index' not found.")
        }

    }
}

internal enum class FontStyle(val index: Int) {
    PLAIN(0),
    BOLD(1),
    ITALIC(2);

    companion object {

        internal fun findByIndex(index: Int): FontStyle {
            return values().firstOrNull { it.index == index }
                ?: throw NoSuchElementException("FontStyle with index '$index' not found.")
        }

    }
}
