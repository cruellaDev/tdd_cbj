package com.study.tdd.chapters.chapter02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void plus () {
        assertEquals(3, Calculator.plus(1, 2));
        assertEquals(5, Calculator.plus(4, 1));
    }
}
