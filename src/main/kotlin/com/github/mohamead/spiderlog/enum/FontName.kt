package com.github.mohamead.spiderlog.enum

internal enum class FontName(val value: String, val index: Int) {
    CONSOLAS("Consolas", 0),
    SEGOE_UI("Segoe UI", 1),
    JETBRAINS_MONO("Jetbrains Mono", 2);

    companion object {

        internal fun findByIndex(index: Int): FontName {
            return values().firstOrNull { it.index == index }
                ?: throw NoSuchElementException("FontName with index '$index' not found.")
        }

    }
}
