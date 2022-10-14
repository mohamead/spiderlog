package com.github.mohamead.spiderlog

import com.github.mohamead.spiderlog.icons.SpiderlogIcons
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SpiderlogIconsTest {

    @Test
    fun toolWindowIconTest() {
        val icon = SpiderlogIcons.toolWindowIcon
        assertAll(
            { assertNotNull(icon) }
        )
    }

}