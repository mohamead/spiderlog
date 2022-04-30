package com.github.mohamead.spiderlog.util

import com.github.mohamead.spiderlog.ui.SpiderToolWindowPanel
import java.beans.PropertyChangeEvent
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.swing.SwingWorker
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableModel

internal class LogTracer {

    internal fun display(spiderlogToolWindowPanel: SpiderToolWindowPanel, file: File) {
        if (file.extension != "log") return
        val model = DefaultTableModel()
        spiderlogToolWindowPanel.subTable.model = model
        val worker = LogWorker(file, model)
        worker.addPropertyChangeListener { e: PropertyChangeEvent ->
            val state: SwingWorker.StateValue = e.newValue as SwingWorker.StateValue
            spiderlogToolWindowPanel.subProgressBar.isIndeterminate = state == SwingWorker.StateValue.STARTED
        }
        worker.execute()
    }

}
