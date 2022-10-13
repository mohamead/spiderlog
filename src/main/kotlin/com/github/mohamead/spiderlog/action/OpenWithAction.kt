package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.LogTracer
import com.github.mohamead.spiderlog.util.clearContent
import com.github.mohamead.spiderlog.util.getToolWindow
import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.github.mohamead.spiderlog.util.validExtension
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import java.awt.EventQueue

internal class OpenWithAction : AnAction(), DumbAware {

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
