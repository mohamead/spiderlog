package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.LogTracer
import com.github.mohamead.spiderlog.util.getSpiderlogToolWindowPanel
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import java.awt.EventQueue
import java.io.File
import javax.swing.table.DefaultTableModel

internal class ForceRefreshAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        val spiderlogToolWindowPanel = getSpiderlogToolWindowPanel(e)
        val model = spiderlogToolWindowPanel.subTable.model ?: return
        val defaultTableModel = model as (DefaultTableModel)
        val pathStr = defaultTableModel.getColumnName(0) ?: return
        val file = File(pathStr)
        EventQueue.invokeLater { LogTracer().display(spiderlogToolWindowPanel, file) }
    }

}
