package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.enum.Direction
import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project

internal abstract class ScrollAction : AnAction(), DumbAware {

    protected fun scrollTo(project: Project, direction: Direction) {
        val table = getToolWindowPanel(project).table
        val rowIndex = when (direction) {
            Direction.END -> table.rowCount - 1
            Direction.TOP -> 0
        }
        if (table.rowCount == 0 || rowIndex > table.rowCount) return
        table.changeSelection(rowIndex, 0, false, false)
    }

}
