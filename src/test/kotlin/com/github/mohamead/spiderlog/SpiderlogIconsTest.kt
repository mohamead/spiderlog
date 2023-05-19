package com.github.mohamead.spiderlog

import com.github.mohamead.spiderlog.icons.SpiderlogIcons
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SpiderlogIconsTest {

    @Test
    fun toolWindowIconTest() {
        val icon = SpiderlogIcons.toolWindowIcon
        assertAll(
            { assertNotNull(icon) }
        )
    }

}