package com.github.mohamead.spiderlog.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "com.github.mohamead.spiderlog.settings.SpiderlogSettingsState",
    storages = [Storage("SdkSettingsPlugin.xml")]
)
internal class SpiderlogSettingsState : PersistentStateComponent<SpiderlogSettingsState> {

    var name = 0
    var style = 0
    var size = 13

    fun getInstance(): SpiderlogSettingsState? {
        return ApplicationManager.getApplication().getService(SpiderlogSettingsState::class.java)
    }

    override fun getState(): SpiderlogSettingsState {
        return this
    }

    override fun loadState(state: SpiderlogSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

}
