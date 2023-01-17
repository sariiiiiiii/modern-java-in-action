package com.study.modernjavainaction.methodReference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferences {

    public static void main(String[] args) {

        /**
         * 메소드를 구현한게 아니라 기존에 있던 메소드를 참조해서 사용하고 있는 것, @FunctionInterface를 사용하고 있는 것
         * :: <- 콜론이 2개 찍혀있으면 메소드 레퍼런스
         */

        // 기존 함수형 인터페이스
        UnaryOperator<String> hi = (s) -> "hi " + s;
        System.out.println(hi.apply("sari"));

        // 스태틱 메소드 레퍼런스를 사용하는 방법
        UnaryOperator<String> hi2 = Greeting::hi;
        System.out.println(hi2.apply("sari"));

        // 인스턴스 메소드 레퍼런스를 사용하는 방법
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("sari"));

        // 생성자 레퍼런스 사용방법 (기본 생성자 참조)
        Supplier<Greeting> newGreeting = Greeting::new; // 아직은 Supplier
        Greeting greeting1 = newGreeting.get();// .get() <타입>을 꺼냄
        System.out.println(greeting1.getClass());

        // Supplier와 같은 경우지만 서로 다른 생성자를 쓰게 됨 (문자열을 받는 생성자 참조)
        Function<String, Greeting> sariGreeting = Greeting::new;
        Greeting funGreeting = sariGreeting.apply("sari");
        System.out.println(funGreeting.getName());

        // 임의 객체의 인스턴스 메소드 참조 (특정 타입이긴 한데 그 타입의 불특정 다수 인스턴스 메소드 참조)
        String[] names = {"toby", "hello", "sari"};

        Arrays.sort(names, new Comparator<String>() { // 익명 내부클래스 참조
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        Arrays.sort(names, String::compareToIgnoreCase); // String의 compareToIgnoreCase 메서드를 참조한 것이 아니라 임의의 객체의 메서드를 참조
        System.out.println(Arrays.toString(names));

    }

}
