package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.LogTracer
import com.github.mohamead.spiderlog.util.clearContent
import com.github.mohamead.spiderlog.util.getSpiderlogToolWindowPanel
import com.github.mohamead.spiderlog.util.openPath
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import java.awt.EventQueue

internal class OpenFileAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        val filePath = openPath(e.project!!, "Open file", "Select any (.log)")
        if (filePath != null) {
            val spiderlogToolWindowPanel = getSpiderlogToolWindowPanel(e)
            spiderlogToolWindowPanel.subTable.clearContent()
            EventQueue.invokeLater { LogTracer().display(spiderlogToolWindowPanel, filePath.toFile()) }
        }
    }

}
