package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.LogTracer
import com.github.mohamead.spiderlog.util.clearContent
import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.github.mohamead.spiderlog.util.openPath
import com.github.mohamead.spiderlog.util.validExtension
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import java.awt.EventQueue

internal class OpenFileAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        val extension = validExtension.joinToString("|")
        val filePath = openPath(e.project!!, "Open file", "Select a file (${extension})") ?: return
        val toolWindowPanel = getToolWindowPanel(e.project!!)
        toolWindowPanel.table.clearContent()
        EventQueue.invokeLater { LogTracer().display(toolWindowPanel, filePath.toFile()) }
    }

}
