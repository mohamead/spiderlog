package com.github.mohamead.spiderlog.setting

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
        val state: SettingState = SettingState().getInstance()!!
        return (settingComponent.getName() != state.name) or (settingComponent.getStyle() != state.style) or (settingComponent.getSize() != state.size)
    }

    override fun apply() {
        val state: SettingState = SettingState().getInstance()!!
        state.name = settingComponent.getName()
        state.style = settingComponent.getStyle()
        state.size = settingComponent.getSize()
    }

    override fun reset() {
        val state: SettingState = SettingState().getInstance()!!
        settingComponent.setName(state.name)
        settingComponent.setStyle(state.style)
        settingComponent.setSize(state.size)
    }

}
