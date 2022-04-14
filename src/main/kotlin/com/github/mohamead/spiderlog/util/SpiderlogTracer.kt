package com.github.mohamead.spiderlog.util

import com.github.mohamead.spiderlog.ui.SpiderlogToolWindowPanel
import java.beans.PropertyChangeEvent
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.swing.SwingWorker
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableModel

internal class SpiderlogTracer {

    private open class SpiderlogWorker(file: File, model: DefaultTableModel) : SwingWorker<TableModel?, String?>() {
        private val file: File
        private val model: DefaultTableModel

        init {
            this.file = file
            this.model = model
            model.setColumnIdentifiers(arrayOf<String>(file.absolutePath))
        }

        override fun doInBackground(): TableModel {
            val buffer = BufferedReader(FileReader(file))
            var line: String?
            while (buffer.readLine().also { line = it } != null) {
                if (line!!.trim().startsWith("at ")) {
                    publish("    " + line!!)
                    continue
                }
                publish(line)
            }
            return model
        }

        override fun process(chunks: MutableList<String?>?) {
            if (chunks == null || chunks.isEmpty()) return
            for (s in chunks) {
                model.addRow(arrayOf(s))
            }
        }
    }

    internal fun display(spiderlogToolWindowPanel: SpiderlogToolWindowPanel, file: File) {
        if (file.extension != "log") return
        val model = DefaultTableModel()
        spiderlogToolWindowPanel.subTable.model = model
        val worker = SpiderlogWorker(file, model)
        worker.addPropertyChangeListener { e: PropertyChangeEvent ->
            val s: SwingWorker.StateValue = e.newValue as SwingWorker.StateValue
            spiderlogToolWindowPanel.subProgressBar.isIndeterminate = s == SwingWorker.StateValue.STARTED
        }
        worker.execute()
    }

}
