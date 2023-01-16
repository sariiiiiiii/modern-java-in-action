package com.study.modernjavainaction.functionalInterface;

import java.util.function.Function;

public class Plus10 implements Function<Integer, Integer> {

    /**
     * Java8에서 제공하는 함수형 인터페이스
     * Function<T, R>
     * T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
     * R apply(T t)
     * 함수 조합용 메소드
     *    andThen
     *    compose
     */

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
