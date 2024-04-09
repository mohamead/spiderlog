package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.clearContent
import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import javax.swing.table.DefaultTableModel

internal class ClearAction : AnAction(), DumbAware {

    override fun actionPerformed(e: AnActionEvent) {
        val table = getToolWindowPanel(e.project!!).table
        table.clearContent()
        if (table.model is DefaultTableModel) {
            val model = table.model as DefaultTableModel
            model.setColumnIdentifiers(arrayOf())
        }
    }

}
