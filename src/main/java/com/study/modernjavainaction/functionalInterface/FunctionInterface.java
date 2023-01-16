package com.study.modernjavainaction.functionalInterface;

import java.util.function.Function;

public class FunctionInterface {

    public static void main(String[] args) {

        /**
         * Function<T, R>을 구현해서 직접 사용할 수 있지만
         */
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1)); // 11

        /**
         * 람다포현식으로 함수형 인터페이스 사용
         */
        Function<Integer, Integer> plus20 = (number) -> number + 20;
        System.out.println(plus20.apply(1)); // 21


        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        /**
         * 함수형 인터페이스 조합식
         * compose = plus20을 실행하기 전에 multiply2를 실행하고 그 값으로 plus20을 실행
         */
        Function<Integer, Integer> multiply2AndPlus20 = plus20.compose(multiply2);
        System.out.println(multiply2AndPlus20.apply(2)); // 24

        /**
         * andThen = plus20를 실행하고 그 값으로 multiply2 실행
         */
        Function<Integer, Integer> plus20Andmultiply2 = plus20.andThen(multiply2);
        System.out.println(plus20Andmultiply2.apply(2)); // 44

    }

}
