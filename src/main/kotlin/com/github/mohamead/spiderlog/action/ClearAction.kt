package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.clearContent
import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware

internal class ClearAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        getToolWindowPanel(e.project!!).table.clearContent()
    }

}
