package com.github.mohamead.spiderlog.setting

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

internal class SettingConfigurable : Configurable {

    private var settingsComponent: SettingComponent? = null

    override fun getDisplayName(): String {
        return "Spiderlog"
    }

    override fun createComponent(): JComponent? {
        settingsComponent = SettingComponent()
        return settingsComponent!!.getPanel()
    }

    override fun isModified(): Boolean {
        val settings: SettingState = SettingState().getInstance()!!
        return (settingsComponent!!.getName() != settings.name) or (settingsComponent!!.getStyle() != settings.style) or (settingsComponent!!.getSize() != settings.size)
    }

    override fun apply() {
        val settings: SettingState = SettingState().getInstance()!!
        settings.name = settingsComponent!!.getName()
        settings.style = settingsComponent!!.getStyle()
        settings.size = settingsComponent!!.getSize()
    }

    override fun reset() {
        val settings: SettingState = SettingState().getInstance()!!
        settingsComponent!!.setName(settings.name)
        settingsComponent!!.setStyle(settings.style)
        settingsComponent!!.setSize(settings.size)
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }

}
