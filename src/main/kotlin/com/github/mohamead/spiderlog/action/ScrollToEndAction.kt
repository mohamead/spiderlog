package com.github.mohamead.spiderlog.action

import com.intellij.openapi.actionSystem.AnActionEvent

internal class ScrollToEndAction : ScrollAction() {

    override fun actionPerformed(e: AnActionEvent) {
        scrollTo(e.project!!, ScrollDirection.END)
    }

}
