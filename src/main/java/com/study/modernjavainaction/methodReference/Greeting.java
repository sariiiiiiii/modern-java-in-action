package com.study.modernjavainaction.methodReference;

public class Greeting {

    private String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    // 인스턴스 메소드
    public String hello(String name) {
        return "hello" + name;
    }

    public String getName() {
        return name;
    }

    // 스태틱 메소드
    public static String hi(String name) {
        return "hi " + name;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "name='" + name + '\'' +
                '}';
    }
}
