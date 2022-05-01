package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.LogTracer
import com.github.mohamead.spiderlog.util.clearContent
import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.github.mohamead.spiderlog.util.getToolWindow
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import java.awt.EventQueue

internal class OpenWithAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        getToolWindow(e).show()
        val toolWindowPanel = getToolWindowPanel(e)
        toolWindowPanel.table.clearContent()

        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        EventQueue.invokeLater { LogTracer().display(toolWindowPanel, virtualFile.toNioPath().toFile()) }
    }

    override fun update(e: AnActionEvent) {
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val isLogFile = (virtualFile != null && !virtualFile.isDirectory && virtualFile.extension != null && virtualFile.extension == "log")
        e.presentation.isEnabledAndVisible = isLogFile
    }

}
