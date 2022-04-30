package com.github.mohamead.spiderlog.ui

import com.github.mohamead.spiderlog.setting.SettingState
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionToolbar
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.JBMenuItem
import com.intellij.openapi.ui.JBPopupMenu
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.table.JBTable
import java.awt.BorderLayout
import java.awt.Font
import java.awt.Robot
import java.awt.event.KeyEvent
import javax.swing.JProgressBar

internal class SpiderToolWindowPanel(toolWindow: ToolWindow) : SimpleToolWindowPanel(false), DumbAware {

    private val subPanel: SimpleToolWindowPanel = SimpleToolWindowPanel(true, true)
    val subTable: JBTable = JBTable()
    val subProgressBar = JProgressBar()

    init {
        toolWindow.activate {
            buildToolbar()
            buildPanel()
        }
    }

    private fun buildToolbar() {
        val actionManager: ActionManager = ActionManager.getInstance()
        val actionToolbar: ActionToolbar = actionManager.createActionToolbar(
            "Spiderlog Navigator Toolbar",
            actionManager.getAction("Spiderlog.NavigatorToolbar") as DefaultActionGroup,
            false
        )
        actionToolbar.layoutPolicy = ActionToolbar.AUTO_LAYOUT_POLICY
        actionToolbar.adjustTheSameSize(true)
        actionToolbar.setShowSeparatorTitles(true)
        this.toolbar = actionToolbar.component
    }

    private fun buildPanel() {
        subPanel.layout = BorderLayout()
        subPanel.add(subProgressBar, BorderLayout.NORTH)
        subTable.font = buildFont()
        subTable.setShowGrid(false)
        subTable.setDefaultEditor(Object::class.java, null)
        subTable.tableHeader.reorderingAllowed = false
        subTable.tableHeader.resizingAllowed = false
        subTable.inheritsPopupMenu = true
        subTable.componentPopupMenu = buildPopupMenu()
        subPanel.add(JBScrollPane(subTable))
        this.add(subPanel)
    }

    private fun buildFont(): Font {
        val state = SettingState().getInstance()!!.state
        val name = if (state.name == 0) "Consoles" else "Segoe UI"
        val type = state.style
        val size = state.size
        return Font(name, type, size)
    }

    private fun buildPopupMenu(): JBPopupMenu {
        val popupMenu = JBPopupMenu()
        val popupMenuCopyMenuItem = JBMenuItem("Copy", AllIcons.Actions.Copy)
        popupMenuCopyMenuItem.addActionListener {
            val selectedRow = subTable.selectedRows
            val selectedColumns = subTable.selectedColumns
            if (selectedRow.isNotEmpty() && selectedColumns.isNotEmpty()) {
                val robot = Robot()
                robot.keyPress(KeyEvent.VK_CONTROL)
                robot.keyPress(KeyEvent.VK_C)
                robot.keyRelease(KeyEvent.VK_CONTROL)
                robot.keyRelease(KeyEvent.VK_C)
            }
        }
        popupMenu.add(popupMenuCopyMenuItem)
        return popupMenu
    }

}
