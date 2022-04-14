package com.github.mohamead.spiderlog.util

import com.github.mohamead.spiderlog.ui.SpiderlogToolWindowFactory
import com.github.mohamead.spiderlog.ui.SpiderlogToolWindowPanel
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import java.nio.file.Path
import java.util.*


internal fun openPath(project: Project, title: String, description: String) : Path? {
    val fileDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
    fileDescriptor.withTitle(title)
    fileDescriptor.withDescription(description)
    fileDescriptor.withShowFileSystemRoots(true)
    fileDescriptor.withTreeRootVisible(true)
    fileDescriptor.withFileFilter { it.extension != null && it.extension == "log" }
    val file = FileChooser.chooseFile(fileDescriptor, project, project.guessProjectDir())
    if (Objects.isNull(file)) {
        return null
    }
    return file!!.toNioPath()
}

internal fun getProjectService(e: AnActionEvent): SpiderlogToolWindowFactory.ProjectService {
    return ServiceManager.getService(
        e.project!!,
        SpiderlogToolWindowFactory.ProjectService::class.java
    )!!
}

internal fun getSpiderlogToolWindowPanel(e: AnActionEvent) : SpiderlogToolWindowPanel {
    return getProjectService(e).spiderlogToolWindowPanel!!
}

internal fun getToolWindow(e: AnActionEvent) : ToolWindow {
    return ToolWindowManager.getInstance(e.project!!).getToolWindow("Spiderlog")!!
}
