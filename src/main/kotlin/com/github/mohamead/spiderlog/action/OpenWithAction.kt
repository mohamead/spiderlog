package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.*
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import java.awt.EventQueue

internal class OpenWithAction : SpiderlogAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        getToolWindow(project).show()
        val toolWindowPanel = getToolWindowPanel(project)
        toolWindowPanel.table.clearContent()

        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        EventQueue.invokeLater { LogTracer().display(toolWindowPanel, virtualFile.toNioPath().toFile()) }
    }

    override fun update(e: AnActionEvent) {
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val isLogFile = (virtualFile != null && !virtualFile.isDirectory &&
                virtualFile.extension != null && validExtension.contains(virtualFile.extension))
        e.presentation.isEnabledAndVisible = isLogFile
    }

}
