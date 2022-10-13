package com.github.mohamead.spiderlog

import com.github.mohamead.spiderlog.enum.Direction
import com.github.mohamead.spiderlog.enum.FontName
import com.github.mohamead.spiderlog.enum.FontStyle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class EnumTest {

    @Test
    fun fontNameTest() {
        assertAll(
            { assertEquals(3, FontName.values().size) },
            { assertThrows<NoSuchElementException> { FontName.findByIndex(3) }}
        )
    }

    @Test
    fun fontStyleTest() {
        assertAll(
            { assertEquals(3, FontStyle.values().size) },
            { assertThrows<NoSuchElementException> { FontStyle.findByIndex(3) }}
        )
    }

    @Test
    fun directionTest() {
        assertEquals(2, Direction.values().size)
    }

}
