package com.github.mohamead.spiderlog.action

import com.intellij.openapi.actionSystem.AnActionEvent

internal class ScrollToTopAction : ScrollAction() {

    override fun actionPerformed(e: AnActionEvent) {
        scrollTo(e, ScrollDirection.TOP)
    }

}
