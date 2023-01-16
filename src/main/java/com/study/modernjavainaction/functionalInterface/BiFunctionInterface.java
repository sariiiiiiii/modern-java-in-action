package com.study.modernjavainaction.functionalInterface;

import java.util.function.BiFunction;

public class BiFunctionInterface {

    public static void main(String[] args) {

        /**
         * BiFunction<T, U, R>
         * T타입과 R타입의 인자를 받아서 U 타입을 리턴
         * Function<T, R>는 인자 1개
         * BiFunction<T, U, R>는 인자 2개
         */

        BiFunction<Integer, String, String> printR = (num, str) -> num + str;
        System.out.println(printR.apply(1, "hello")); // 1hello

    }

}
