package com.study.modernjavainaction.lambda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaStudy1 {
    public static void main(String[] args) {

        /**
         * (인자 리스트) -> { 바디 }
         * 인자가 없을 때: ()
         * 인자가 한개일 때: (one) 또는 one
         * 인자가 여러개 일 때: (one, two)
         * 인자의 타입은 생략 가능, 컴파일러가 추론(infer) 하지만 명시할 수도 있다. (Integer one, Integer two)
         *
         *  = 바디
         * 화살표 오른쪽에 함수 본문을 정의한다
         * 여러 줄인 경우에 {}를 사용해서 묶는다
         * 한 줄인 경우에 생략 가능, return도 생략 가능
         */

        Apple apple1 = new Apple("green", 150);
        Apple apple2 = new Apple("red", 1000);

        List<Apple> apples = Arrays.asList(apple1, apple2);

        List<Apple> apples1 = filterApples(apples, apple -> isHeavyApple(apple));
        for (Apple apple : apples1) {
            System.out.println("apple = " + apple.toString());
        }
        List<Apple> apples2 = filterApples(apples, LambdaStudy1::isGreenApple); // filterApples(List 인자, 해당 클래스의 메서드)
        for (Apple apple : apples2) {
            System.out.println("apple = " + apple);
        }
        List<Apple> apples3 = filterApples(apples, apple -> GREEN.equals(apple.getColor()));
        for (Apple apple : apples3) {
            System.out.println("apple = " + apple);
        }
        List<Apple> apples4 = filterApples(apples, apple -> apple.getWeight() > 150);
        for (Apple apple : apples4) {
            System.out.println("apple = " + apple);
        }
        List<Apple> apples5 = filterApples(apples, apple -> apple.getWeight() > 150 || GREEN.equals(apple.getColor()));
        for (Apple apple : apples5) {
            System.out.println("apple = " + apple);
        }
    }
    public static final String GREEN = "green";

    @Getter
    @ToString
    @AllArgsConstructor
    static class Apple {
        private String color;
        private int weight;
    }

    interface Predicate<T> {
        Boolean test(T t);
    }

    /**
     * @param inventory LIST
     * @param predicate interface를 인자로 받아 활용
     * @return
     */
    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 기존 방식
     */
    static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    static Boolean isGreenApple(Apple apple) {
        return GREEN.equals(apple.getColor());
    }

    static Boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

}
