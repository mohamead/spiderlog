package com.github.mohamead.spiderlog.util

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.swing.SwingWorker
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableModel

internal class LogWorker(file: File, model: DefaultTableModel) : SwingWorker<TableModel, String>() {
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

    override fun process(chunks: MutableList<String>) {
        if (chunks.isEmpty()) return
        for (chunk in chunks) {
            model.addRow(arrayOf(chunk))
        }
    }
}
