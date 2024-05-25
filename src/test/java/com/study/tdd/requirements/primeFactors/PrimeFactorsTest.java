package com.study.tdd.requirements.primeFactors;

import static com.study.tdd.requirements.primeFactors.PrimeFactors.generate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PrimeFactorsTest {

    private List<Integer> list(int... ints) {
        List<Integer> list = new ArrayList<>();
        for (int i : ints) {
            list.add(i);
        }
        return list;
    }

    @Disabled
    @Test
    void init() {
        List<Integer> integerList = generate(0);
        assertNull(integerList);
    }

    @Test
    void testOne() throws  Exception {
        assertEquals(list(), generate(1));
    }

    @Test
    void testTwo() throws  Exception {
        assertEquals(list(2), generate(2));
    }

    @Test
    void testThress() throws Exception {
        assertEquals(list(3), generate(3));
    }

    @Test
    void testFour() throws Exception {
        assertEquals(list(2, 2), generate(4));
    }

    @Test
    void testSix() throws Exception {
        assertEquals(list(2, 3), generate(6));
    }

    @Test
    void testEight() throws Exception {
        assertEquals(list(2, 2, 2), generate(8));
    }

    @Test
    void testNine() throws Exception {
        assertEquals(list(3, 3), generate(9));
    }
}
