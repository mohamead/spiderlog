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

internal class ToolWindowPanel(toolWindow: ToolWindow) : SimpleToolWindowPanel(false), DumbAware {

    private val panel: SimpleToolWindowPanel = SimpleToolWindowPanel(true, true)
    val table: JBTable = JBTable()
    val progressBar = JProgressBar()

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
        panel.layout = BorderLayout()
        panel.add(progressBar, BorderLayout.NORTH)
        table.font = buildFont()
        table.setShowGrid(false)
        table.setDefaultEditor(Object::class.java, null)
        table.tableHeader.reorderingAllowed = false
        table.tableHeader.resizingAllowed = false
        table.inheritsPopupMenu = true
        table.componentPopupMenu = buildPopupMenu()
        panel.add(JBScrollPane(table))
        this.add(panel)
    }

    private fun buildFont() : Font {
        val state = SettingState().getInstance().state
        return Font(state.fontName.value, state.fontStyle.index, state.fontSize)
    }

    private fun buildPopupMenu(): JBPopupMenu {
        val popupMenu = JBPopupMenu()
        val popupMenuCopyMenuItem = JBMenuItem("Copy", AllIcons.Actions.Copy)
        popupMenuCopyMenuItem.addActionListener {
            val selectedRow = table.selectedRows
            val selectedColumns = table.selectedColumns
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

    internal fun updateUi() {
        table.font = buildFont()
        table.updateUI()
    }

}
