package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.LogTracer
import com.github.mohamead.spiderlog.util.clearContent
import com.github.mohamead.spiderlog.util.getSpiderlogToolWindowPanel
import com.github.mohamead.spiderlog.util.getToolWindow
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import java.awt.EventQueue

internal class OpenWithAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        getToolWindow(e).show()
        val spiderlogToolWindowPanel = getSpiderlogToolWindowPanel(e)
        spiderlogToolWindowPanel.subTable.clearContent()

        val file = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        val filePath = file.toNioPath()
        EventQueue.invokeLater { LogTracer().display(spiderlogToolWindowPanel, filePath.toFile()) }
    }

    override fun update(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val isLogFile = (file != null && !file.isDirectory && file.extension != null && file.extension == "log")
        e.presentation.isEnabledAndVisible = isLogFile
    }

}
