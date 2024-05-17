package com.study.tdd.requirements;

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
