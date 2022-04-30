package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.*
import com.github.mohamead.spiderlog.util.LogTracer
import com.github.mohamead.spiderlog.util.getSpiderlogToolWindowPanel
import com.github.mohamead.spiderlog.util.getToolWindow
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
        val filePath = openPath(e.project!!, "Open file", "Select any (.log)")
        if (filePath != null) {
            val spiderlogToolWindowPanel = getSpiderlogToolWindowPanel(e)
            spiderlogToolWindowPanel.subTable.clearContent()
            EventQueue.invokeLater { LogTracer().display(spiderlogToolWindowPanel, filePath.toFile()) }
        }
    }
}

internal class ScrollToTopAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val subTable = getSpiderlogToolWindowPanel(e).subTable
        if (subTable.rowCount == 0) return
        subTable.changeSelection(0, 0, false, false)
    }
}

internal class ScrollToEndAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val subTable = getSpiderlogToolWindowPanel(e).subTable
        if (subTable.rowCount == 0) return
        subTable.changeSelection(subTable.rowCount - 1, 0, false, false)
    }
}

internal class ClearAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        getSpiderlogToolWindowPanel(e).subTable.clearContent()
    }
}

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
