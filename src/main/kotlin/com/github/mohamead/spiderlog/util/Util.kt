package com.github.mohamead.spiderlog.util

import com.github.mohamead.spiderlog.ui.ProjectService
import com.github.mohamead.spiderlog.ui.ToolWindowPanel
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.table.JBTable
import java.io.File
import java.nio.file.Path
import java.util.*
import javax.swing.table.DefaultTableModel


internal fun openPath(project: Project, title: String, description: String) : Path? {
    val fileDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
    fileDescriptor.withTitle(title)
    fileDescriptor.withDescription(description)
    fileDescriptor.withShowFileSystemRoots(true)
    fileDescriptor.withTreeRootVisible(true)
    fileDescriptor.withFileFilter { it.extension != null && it.extension == "log" }
    val file = FileChooser.chooseFile(fileDescriptor, project, project.guessProjectDir())
    return file?.toNioPath()
}

internal fun getProjectService(e: AnActionEvent): ProjectService {
    return ServiceManager.getService(e.project!!, ProjectService::class.java)!!
}

internal fun getToolWindowPanel(e: AnActionEvent) : ToolWindowPanel {
    return getProjectService(e).toolWindowPanel
}

internal fun getToolWindow(e: AnActionEvent) : ToolWindow {
    return ToolWindowManager.getInstance(e.project!!).getToolWindow("Spiderlog")!!
}

internal fun JBTable.clearContent() {
    val model = this.model ?: return
    val filePath = model.getColumnName(0) ?: return
    val file = File(filePath)
    if (!file.isFile || model !is DefaultTableModel) return
    model.setColumnIdentifiers(arrayOf())
    model.dataVector.removeAllElements()
    model.fireTableDataChanged()
}
