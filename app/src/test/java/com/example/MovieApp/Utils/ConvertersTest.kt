package com.example.MovieApp.Utils

import org.junit.Assert.*
import org.junit.Test

class ConvertersTest {
    private val converters = Converters()

    @Test
    fun testFromList() {
        val list = listOf(1, 2, 3)
        val result = converters.fromList(list)
        assertEquals("1,2,3", result)
    }

    @Test
    fun testFromList_empty() {
        val list = emptyList<Int>()
        val result = converters.fromList(list)
        assertEquals("", result)
    }



}