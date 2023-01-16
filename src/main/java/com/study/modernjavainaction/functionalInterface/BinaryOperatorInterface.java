package com.study.modernjavainaction.functionalInterface;

import java.util.function.BinaryOperator;

public class BinaryOperatorInterface {

    public static void main(String[] args) {

        /**
         * BinaryOperator<T>
         * BiFunction<T, T, T> BiFunction의 2개의 인자와 리턴타입이 같은 타입일 때
         */

//        BinaryOperator<Integer> plus10And20 = Integer::sum; 축약형
        BinaryOperator<Integer> plus10And20 = (num1, num2) -> num1 + num2;
        System.out.println(plus10And20.apply(10, 20)); // 30

    }

}
