package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.LogTracer
import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import java.awt.EventQueue
import java.io.File
import javax.swing.table.DefaultTableModel

internal class ForceRefreshAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        val toolWindowPanel = getToolWindowPanel(e)
        val model = toolWindowPanel.table.model ?: return
        val defaultTableModel = model as (DefaultTableModel)
        val filePath = defaultTableModel.getColumnName(0) ?: return
        val file = File(filePath)
        toolWindowPanel.updateUi()
        EventQueue.invokeLater { LogTracer().display(toolWindowPanel, file) }
    }

}
