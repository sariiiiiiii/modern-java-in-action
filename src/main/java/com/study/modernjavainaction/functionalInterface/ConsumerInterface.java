package com.study.modernjavainaction.functionalInterface;

import java.util.function.Consumer;

public class ConsumerInterface {

    public static void main(String[] args) {

        /**
         * Consumer<T>
         * return값이 void인 함수형 인터페이스
         */

//        Consumer<Integer> printT = (number) -> System.out.println(number);
        Consumer<Integer> printT = System.out::println; // 축약
        printT.accept(10); // 10

    }

}
