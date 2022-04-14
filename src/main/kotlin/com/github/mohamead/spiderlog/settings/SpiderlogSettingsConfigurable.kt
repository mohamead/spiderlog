package com.github.mohamead.spiderlog.settings

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

internal class SpiderlogSettingsConfigurable : Configurable {

    private var settingsComponent: SpiderlogSettingsComponent? = null

    override fun getDisplayName(): String {
        return "Spiderlog"
    }

    override fun createComponent(): JComponent? {
        settingsComponent = SpiderlogSettingsComponent()
        return settingsComponent!!.getPanel()
    }

    override fun isModified(): Boolean {
        val settings: SpiderlogSettingsState = SpiderlogSettingsState().getInstance()!!
        return (settingsComponent!!.getName() != settings.name) or (settingsComponent!!.getStyle() != settings.style) or (settingsComponent!!.getSize() != settings.size)
    }

    override fun apply() {
        val settings: SpiderlogSettingsState = SpiderlogSettingsState().getInstance()!!
        settings.name = settingsComponent!!.getName()
        settings.style = settingsComponent!!.getStyle()
        settings.size = settingsComponent!!.getSize()
    }

    override fun reset() {
        val settings: SpiderlogSettingsState = SpiderlogSettingsState().getInstance()!!
        settingsComponent!!.setName(settings.name)
        settingsComponent!!.setStyle(settings.style)
        settingsComponent!!.setSize(settings.size)
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }

}
