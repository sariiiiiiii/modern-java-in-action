package com.study.modernjavainaction.functionalInterface;

import java.util.function.UnaryOperator;

public class UnaryOperatorInterface {

    public static void main(String[] args) {

        /**
         * UnaryOperator<T>
         * 입력값의 값과 리턴값의 값이 같으면 T 하나로 축약하는 함수형 인터페이스
         *
         * UnaryOperator는 Function<T, R>을 상속받았기 때문에 Function<T, R>에서 제공하는 andThen, compose를 사용할 수 있음
         */

        UnaryOperator<Integer> plus10 = (number) -> number + 10;
        System.out.println(plus10.apply(1)); // 11

    }

}
