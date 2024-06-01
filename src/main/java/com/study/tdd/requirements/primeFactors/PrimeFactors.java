package com.study.tdd.requirements.primeFactors;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * The Prime Factors Kata
 * </pre>
 * @version 1.0
 * @author Lee, Ji Won
 * @since 2024.05.25
 * @link    <a href="http://butunclebob.com/ArticleS.UncleBob.ThePrimeFactorsKata">TDD Kata | PrimeFactors</a>
 */
public class PrimeFactors {
    public static List<Integer> generate(int n) {
        List<Integer> primes = new ArrayList<>();

        /*
        // refactoring while -> for
        int candidate = 2;
        while (n > 1) {
            while (n % candidate == 0) {
                primes.add(candidate);
                n /= candidate;
            }
            candidate++;
        }
         */

        for (int candidate = 2; n > 1; candidate++) {
            for (;n % candidate == 0; n /= candidate) {
                primes.add(candidate);
            }
        }
        return primes;
    }
}
