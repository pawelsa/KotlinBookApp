package com.example.kotlin_bookapp

import com.example.kotlin_bookapp.extensions.toDateString
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.DateFormat

class ExtensionsTest {

    @Test
    fun `"longToDateString" returns valid value`(){
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }

    @Test
    fun `"longToDateString" with full format returns valid value`(){
        assertEquals("poniedziałek, 19 października 2015", 1445275635000L.toDateString(DateFormat.FULL))
    }

}