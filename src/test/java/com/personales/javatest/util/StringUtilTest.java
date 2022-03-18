package com.personales.javatest.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void repeat_string_once(){
        assertEquals("hola", StringUtil.repeat("hola", 1));
    }

    @Test
    public void repeat_string_multipleTimes(){
        assertEquals("holahola", StringUtil.repeat("hola", 2));
    }

    @Test
    public void repeat_string_zero_times(){
        assertEquals("", StringUtil.repeat("hola", 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void repeat_string_negative_times(){
        StringUtil.repeat("hola", -1);
    }

    @Test
    public void test_string_is_not_empty(){
        assertFalse(StringUtil.isEmpty("DOG"));
    }

    @Test
    public void empty_quote_string_is_empty() {
        assertTrue(StringUtil.isEmpty(""));
    }

    @Test
    public void null_is_string() {
        assertTrue(StringUtil.isEmpty(null));
    }

    @Test
    public void string_spaces_is_string_empty(){
        assertTrue(StringUtil.isEmpty("   "));
    }
}