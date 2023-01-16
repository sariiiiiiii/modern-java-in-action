package com.study.modernjavainaction.functionalInterface;

import java.util.function.Supplier;

public class SupplierInterface {

    public static void main(String[] args) {

        /**
         * Supplier<T>
         * 매개변수를 받지 않고 <T>형의 return만 있는 함수형 인터페이스
         */

        Supplier<Integer> get10 = () -> 10;
        Supplier<String> getHello = () -> "hello";

        System.out.println(get10.get()); // 10
        System.out.println(getHello.get()); // hello

    }

}
