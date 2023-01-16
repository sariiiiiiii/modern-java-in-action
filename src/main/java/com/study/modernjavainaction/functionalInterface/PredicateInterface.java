package com.study.modernjavainaction.functionalInterface;

import java.util.function.Predicate;

public class PredicateInterface {

    public static void main(String[] args) {

        /**
         * Predicate<T>
         * T 인자를 받아서 true, false를 return 해주는 함수형 인터페이스
         * or, and, negate 등으로 조합 가능
         */

        Predicate<String> startsWithSari = (s) -> s.startsWith("sari");
        Predicate<Integer> isEven = (i) -> i % 2 == 0;

        System.out.println(startsWithSari.test("sari11")); // true
        System.out.println(isEven.test(3)); // false

    }
}
