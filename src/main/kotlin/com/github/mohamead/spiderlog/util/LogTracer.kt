package com.github.mohamead.spiderlog.util

import com.github.mohamead.spiderlog.ui.SpiderlogToolWindowPanel
import java.beans.PropertyChangeEvent
import java.io.File
import javax.swing.SwingWorker
import javax.swing.table.DefaultTableModel

internal class LogTracer {

    internal fun display(toolWindowPanel: SpiderlogToolWindowPanel, file: File) {
        val model = DefaultTableModel()
        toolWindowPanel.table.model = model
        val worker = LogWorker(file, model)
        worker.addPropertyChangeListener { e: PropertyChangeEvent ->
            val state: SwingWorker.StateValue = e.newValue as SwingWorker.StateValue
            toolWindowPanel.progressBar.isIndeterminate = state == SwingWorker.StateValue.STARTED
        }
        worker.execute()
    }

}
