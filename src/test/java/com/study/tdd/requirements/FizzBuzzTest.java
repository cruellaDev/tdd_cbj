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

    private boolean isVerifiedStrByNum(int number, String str) {
        return switch (str) {
            case "Fizz" -> isFizz(number);
            case "Buzz" -> isBuzz(number);
            case "FizzBuzz" -> isFizzBuzz(number);
            default -> str.equals(String.valueOf(number));
        };
    }

    private void assertFizzBuzzPass(int number, String expStr) {
        assertEquals(expStr, FizzBuzz.answer(number));
        boolean condition = isVerifiedStrByNum(number, expStr);
        assertTrue(condition);
    }

    private void assertFizzBuzzFail(int number, String expStr) {
        assertNotEquals(expStr, FizzBuzz.answer(number));
        boolean condition = isVerifiedStrByNum(number, expStr);
        assertFalse(condition);
    }

    @Test
    void testIfFizzPass() {
        assertFizzBuzzPass(3, "Fizz");
        assertFizzBuzzPass(57, "Fizz");
        assertFizzBuzzPass(99, "Fizz");
    }

    @Test
    void testIfFizzFail() {
        assertFizzBuzzFail(20, "Fizz");
        assertFizzBuzzFail(45, "Fizz");
        assertFizzBuzzFail(88, "Fizz");
    }

    @Test
    void testIfBuzzPass() {
        assertFizzBuzzPass(5, "Buzz");
        assertFizzBuzzPass(10, "Buzz");
        assertFizzBuzzPass(100, "Buzz");
    }

    @Test
    void testIfBuzzFail() {
        assertFizzBuzzFail(81, "Buzz");
        assertFizzBuzzFail(75, "Buzz");
        assertFizzBuzzFail(29, "Buzz");
    }

    @Test
    void testIfFizzBuzzPass() {
        assertFizzBuzzPass(75, "FizzBuzz");
        assertFizzBuzzPass(90, "FizzBuzz");
        assertFizzBuzzPass(15, "FizzBuzz");
    }

    @Test
    void testIfFizzBuzzFail() {
        assertFizzBuzzFail(23, "FizzBuzz");
        assertFizzBuzzFail(84, "FizzBuzz");
        assertFizzBuzzFail(35, "FizzBuzz");
    }

    @Test
    void testIfFizzBuzzValid() {
        String actual;
        boolean isVerified = false;

        for (int i = 1; i <= 100; i++) {
            actual = FizzBuzz.answer(i);
            isVerified = isVerifiedStrByNum(i, actual);

            if (!isVerified) throw new AssertionError(i);
        }

        assertTrue(isVerified);
    }
}
