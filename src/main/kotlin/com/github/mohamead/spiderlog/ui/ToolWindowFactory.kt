package com.github.mohamead.spiderlog.ui

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory

internal class ToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val spiderlogToolWindowPanel = SpiderToolWindowPanel(toolWindow)

        val projectService = ServiceManager.getService(project, ProjectService::class.java)
        projectService.spiderlogToolWindowPanel = spiderlogToolWindowPanel

        val contentFactory: ContentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(spiderlogToolWindowPanel, "", false)
        toolWindow.contentManager.addContent(content)
    }

}

