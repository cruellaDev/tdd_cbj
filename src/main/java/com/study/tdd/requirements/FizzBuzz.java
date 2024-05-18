package com.study.tdd.requirements;

/**
 * <pre>
 * Write a program that prints the numbers from 1 to 100.
 * But for multiples of three print "Fizz" instead of the number and for the multiples of five print "Buzz".
 * For numbers which are multiples of both three and five print "FizzBuzz".
 *
 * Sample output:
 *
 * 1
 * 2
 * Fizz
 * 4
 * Buzz
 * Fizz
 * 7
 * 8
 * Fizz
 * ... etc up to 100
 * </pre>
 * @version 1.0
 * @author Lee, Ji Won
 * @since 2024.05.18
 * @link    <a href="https://cyber-dojo.org/creator/home">cyber-dojo</a>
 */
public class FizzBuzz {

    public enum Types {
        FIZZ(3, "Fizz"),
        BUZZ(5, "Buzz");

        private final int    divider;
        private final String name;

        Types(int divider, String name) {
            this.divider = divider;
            this.name    = name;
        }

        public int getDivider() {
            return divider;
        }

        public String getName() {
            return name;
        }
    }

    public static String answer(int number) {
        String answer = "";

        if (number % Types.FIZZ.getDivider() == 0) {
            answer += Types.FIZZ.getName();
        }
        if (number % Types.BUZZ.getDivider() == 0) {
            answer += Types.BUZZ.getName();
        }

        if (answer.isEmpty()) {
            answer += String.valueOf(number);
        }

        return answer;
    }
}
