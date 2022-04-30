package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.getSpiderlogToolWindowPanel
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware

class ScrollToTopAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        val subTable = getSpiderlogToolWindowPanel(e).subTable
        if (subTable.rowCount == 0) return
        subTable.changeSelection(0, 0, false, false)
    }

}
