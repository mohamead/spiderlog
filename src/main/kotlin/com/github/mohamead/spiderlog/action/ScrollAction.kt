package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.util.getToolWindowPanel
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware

internal abstract class ScrollAction : AnAction(), DumbAware {

    protected fun scrollTo(e: AnActionEvent, direction: ScrollDirection) {
        val table = getToolWindowPanel(e).table
        val rowIndex = when(direction) {
            ScrollDirection.END -> table.rowCount - 1
            ScrollDirection.TOP -> 0
        }
        if (table.rowCount == 0 || rowIndex > table.rowCount) return
        table.changeSelection(rowIndex, 0, false, false)
    }

}

internal enum class ScrollDirection {
    END,
    TOP
}
