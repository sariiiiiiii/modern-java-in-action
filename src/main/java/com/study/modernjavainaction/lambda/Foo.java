package com.study.modernjavainaction.lambda;

import com.study.modernjavainaction.functionalInterface.Plus10;

public class Foo {

    public static void main(String[] args) {

        /**
         * 자바 8버전 이전에는 익명내부클래스 사용
         */
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("sari");
            }
        };

        /**
         * 자바 8 이후의 lambda 표현식
         */
        RunSomething runSomething1 = () -> System.out.println("sari");
        runSomething1.doIt();

    }

}
