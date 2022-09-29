package com.github.mohamead.spiderlog.setting

import com.github.mohamead.spiderlog.util.getProject
import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

internal class SettingConfigurable : Configurable {

    private lateinit var settingComponent: SettingComponent

    override fun getDisplayName(): String {
        return "Spiderlog"
    }

    override fun createComponent(): JComponent {
        settingComponent = SettingComponent()
        return settingComponent.getPanel()
    }

    override fun isModified(): Boolean {
        val state: SettingState = SettingState().getInstance()
        return (settingComponent.getFontName() != state.fontName ||
                settingComponent.getFontStyle() != state.fontStyle ||
                settingComponent.getFontSize() != state.fontSize)
    }

    override fun apply() {
        val state: SettingState = SettingState().getInstance()
        state.fontName = settingComponent.getFontName()
        state.fontStyle = settingComponent.getFontStyle()
        state.fontSize = settingComponent.getFontSize()
        update()
    }

    override fun reset() {
        val state: SettingState = SettingState().getInstance()
        settingComponent.setFontName(state.fontName)
        settingComponent.setFontStyle(state.fontStyle)
        settingComponent.setFontSize(state.fontSize)
    }

    private fun update() {
        val project = getProject()
        val toolWindowPanel = getToolWindowPanel(project)
        toolWindowPanel.updateUi()
    }

}
