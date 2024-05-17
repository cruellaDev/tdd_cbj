package com.study.tdd.requirements;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FizzBuzzTest {

    private boolean isFizz(int number) {
        return number % 3 == 0 && number % 5 != 0;
    }

    private boolean isBuzz(int number) {
        return number % 5 == 0 && number % 3 != 0;
    }

    private boolean isFizzBuzz(int number) {
        return number % 3 == 0 && number % 5 == 0;
    }

    private void assertFizzBuzzPass(int number, String expStr, boolean condition) {
        assertEquals(expStr, FizzBuzz.answer(number));
        assertTrue(condition);
    }

    private void assertFizzBuzzFail(int number, String expStr, boolean condition) {
        assertNotEquals(expStr, FizzBuzz.answer(number));
        assertFalse(condition);
    }

    @Test
    void testIfFizzPass() {
        assertFizzBuzzPass(3, "Fizz", isFizz(3));
        assertFizzBuzzPass(57, "Fizz", isFizz(57));
        assertFizzBuzzPass(99, "Fizz", isFizz(99));
    }

    @Test
    void testIfFizzFail() {
        assertFizzBuzzFail(20, "Fizz", isFizz(20));
        assertFizzBuzzFail(45, "Fizz", isFizz(45));
        assertFizzBuzzFail(88, "Fizz", isFizz(88));
    }

    @Test
    void testIfBuzzPass() {
        assertFizzBuzzPass(5, "Buzz", isBuzz(5));
        assertFizzBuzzPass(10, "Buzz", isBuzz(10));
        assertFizzBuzzPass(100, "Buzz", isBuzz(100));
    }

    @Test
    void testIfBuzzFail() {
        assertFizzBuzzFail(81, "Buzz", isBuzz(81));
        assertFizzBuzzFail(75, "Buzz", isBuzz(75));
        assertFizzBuzzFail(29, "Buzz", isBuzz(29));
    }

    @Test
    void testIfFizzBuzzPass() {
        assertFizzBuzzPass(75, "FizzBuzz", isFizzBuzz(75));
        assertFizzBuzzPass(90, "FizzBuzz", isFizzBuzz(90));
        assertFizzBuzzPass(15, "FizzBuzz", isFizzBuzz(15));
    }

    @Test
    void testIfFizzBuzzFail() {
        assertFizzBuzzFail(23, "FizzBuzz", isFizzBuzz(23));
        assertFizzBuzzFail(84, "FizzBuzz", isFizzBuzz(84));
        assertFizzBuzzFail(35, "FizzBuzz", isFizzBuzz(35));
    }

    @Test
    void testIfFizzBuzzValid() {
        String actual;
        boolean isValid = false;

        for (int i = 1; i <= 100; i++) {
            actual = FizzBuzz.answer(i);

            isValid = switch (actual) {
                case "Fizz" -> isFizz(i);
                case "Buzz" -> isBuzz(i);
                case "FizzBuzz" -> isFizzBuzz(i);
                default -> actual.equals(String.valueOf(i));
            };

            if (!isValid) throw new AssertionError(i);
        }

        assertTrue(isValid);
    }
}
