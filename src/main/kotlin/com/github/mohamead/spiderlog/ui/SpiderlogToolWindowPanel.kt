package com.github.mohamead.spiderlog.ui

import com.github.mohamead.spiderlog.setting.SettingState
import com.github.mohamead.spiderlog.util.getActionGroup
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionToolbar
import com.intellij.openapi.actionSystem.toolbarLayout.ToolbarLayoutStrategy
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.JBMenuItem
import com.intellij.openapi.ui.JBPopupMenu
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.table.JBTable
import java.awt.BorderLayout
import java.awt.Font
import java.awt.Robot
import java.awt.event.KeyEvent
import javax.swing.JProgressBar

internal class SpiderlogToolWindowPanel : SimpleToolWindowPanel(false), DumbAware {

    internal val table: JBTable
    internal val progressBar: JProgressBar

    init {
        this.toolbar = buildToolbar().component
        this.table = buildTable()
        this.progressBar = JProgressBar()
        val panel = buildPanel().apply {
            add(progressBar)
            add(JBScrollPane(table))
        }
        this.add(panel)
    }

    private fun buildToolbar(): ActionToolbar {
        return ActionManager.getInstance()
            .createActionToolbar("Spiderlog Navigator Toolbar", getActionGroup("Spiderlog.NavigatorToolbar"), false)
            .apply {
                layoutPolicy = ActionToolbar.AUTO_LAYOUT_POLICY
                adjustTheSameSize(true)
                setShowSeparatorTitles(true)
                targetComponent = this@SpiderlogToolWindowPanel
            }
    }

    private fun buildPanel(): SimpleToolWindowPanel {
        return SimpleToolWindowPanel(true, true).apply {
            layout = BorderLayout()
        }
    }

    private fun buildTable(): JBTable {
        return JBTable().apply {
            font = buildFont()
            componentPopupMenu = buildPopupMenu()
            tableHeader.reorderingAllowed = false
            tableHeader.resizingAllowed = false
            inheritsPopupMenu = true
            setShowGrid(false)
            setDefaultEditor(Object::class.java, null)
        }
    }

    private fun buildFont(): Font {
        val state = SettingState().getInstance().state
        return Font(state.fontName, state.fontStyle.index, state.fontSize)
    }

    private fun buildPopupMenu(): JBPopupMenu {
        return JBPopupMenu().apply {
            val popupMenuCopyMenuItem = JBMenuItem("Copy", AllIcons.Actions.Copy).apply {
                this.addActionListener {
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
            }
            this.add(popupMenuCopyMenuItem)
        }
    }

    internal fun updateUi() {
        table.font = buildFont()
        table.updateUI()
    }

}
