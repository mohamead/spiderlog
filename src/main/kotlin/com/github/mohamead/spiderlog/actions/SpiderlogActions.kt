package com.github.mohamead.spiderlog.actions

import com.github.mohamead.spiderlog.util.SpiderlogTracer
import com.github.mohamead.spiderlog.util.getProjectService
import com.github.mohamead.spiderlog.util.getToolWindow
import com.github.mohamead.spiderlog.util.getSpiderlogToolWindowPanel
import com.github.mohamead.spiderlog.util.openPath
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import java.awt.EventQueue
import java.io.File
import javax.swing.table.DefaultTableModel

internal class OpenFileAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val projectService = getProjectService(e)
        val spiderlogToolWindowPanel = projectService.spiderlogToolWindowPanel!!
        val filePath = openPath(e.project!!, "Open file", "Select any (.log)")
        if (filePath != null) {
            spiderlogToolWindowPanel.subTable.removeAll()
            EventQueue.invokeLater { SpiderlogTracer().display(spiderlogToolWindowPanel, filePath.toFile()) }
        }
    }
}

internal class ScrollToTopAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val topTable = getSpiderlogToolWindowPanel(e).subTable
        if (topTable.rowCount == 0) return
        val firstRow = 0
        topTable.changeSelection(firstRow, 0, false, false)
    }
}

internal class ScrollToEndAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val topTable = getSpiderlogToolWindowPanel(e).subTable
        if (topTable.rowCount == 0) return
        val lastRow = topTable.rowCount - 1
        topTable.changeSelection(lastRow, 0, false, false)
    }
}

internal class ClearAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val model = getSpiderlogToolWindowPanel(e).subTable.model ?: return
        val defaultTableModel = model as (DefaultTableModel)
        defaultTableModel.setColumnIdentifiers(arrayOf())
        defaultTableModel.dataVector.removeAllElements()
        defaultTableModel.fireTableDataChanged()
    }
}

internal class OpenWithAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val spiderlogToolWindow = getToolWindow(e)
        spiderlogToolWindow.show()
        val spiderlogToolWindowPanel = getSpiderlogToolWindowPanel(e)
        spiderlogToolWindowPanel.subTable.removeAll()

        val file = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        val filePath = file.toNioPath()
        EventQueue.invokeLater { SpiderlogTracer().display(spiderlogToolWindowPanel, filePath.toFile()) }
    }

    override fun update(e: AnActionEvent) {
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val isLogFile = (file != null && !file.isDirectory && file.extension != null && file.extension == "log")
        e.presentation.isEnabledAndVisible = isLogFile
    }
}

internal class ForceRefreshAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val spiderlogToolWindowPanel = getSpiderlogToolWindowPanel(e)
        val model = spiderlogToolWindowPanel.subTable.model ?: return
        val defaultTableModel = model as (DefaultTableModel)
        val pathStr = defaultTableModel.getColumnName(0) ?: return
        val file = File(pathStr)
        EventQueue.invokeLater { SpiderlogTracer().display(spiderlogToolWindowPanel, file) }
    }
}
