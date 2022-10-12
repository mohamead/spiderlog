package com.github.mohamead.spiderlog.util

import com.github.mohamead.spiderlog.ui.ToolWindowPanel
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.table.JBTable
import java.awt.Window
import java.io.File
import java.nio.file.Path
import javax.swing.table.DefaultTableModel

internal val validExtension: List<String> = listOf("log", "out", "txt")

internal fun openPath(project: Project, title: String, description: String): Path? {
    val fileDescriptor = FileChooserDescriptorFactory.createSingleFileDescriptor()
    fileDescriptor.withTitle(title)
    fileDescriptor.withDescription(description)
    fileDescriptor.withShowFileSystemRoots(true)
    fileDescriptor.withTreeRootVisible(true)
    fileDescriptor.withFileFilter { it.extension != null && validExtension.contains(it.extension) }
    val file = FileChooser.chooseFile(fileDescriptor, project, project.guessProjectDir())
    return file?.toNioPath()
}

internal fun getToolWindowPanel(project: Project): ToolWindowPanel {
    return getToolWindow(project).contentManager.getContent(0)!!.component as ToolWindowPanel
}

internal fun getToolWindow(project: Project): ToolWindow {
    return ToolWindowManager.getInstance(project).getToolWindow("Spiderlog")!!
}

internal fun getProject() : Project {
    val projects = ProjectManager.getInstance().openProjects
    var activeProject: Project? = null
    for (project in projects) {
        val window: Window? = WindowManager.getInstance().suggestParentWindow(project)
        if (window != null && window.isActive) {
            activeProject = project
        }
    }
    return activeProject!!
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
