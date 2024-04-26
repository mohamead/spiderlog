package com.github.mohamead.spiderlog.action

import com.github.mohamead.spiderlog.enum.Direction
import com.intellij.openapi.actionSystem.AnActionEvent

internal class ScrollToEndAction : ScrollAction() {

    override fun actionPerformed(e: AnActionEvent) = scrollTo(e.project!!, Direction.END)

}
