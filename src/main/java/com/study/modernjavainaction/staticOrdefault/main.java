package com.study.modernjavainaction.staticOrdefault;

public class main {

    public static void main(String[] args) {

        Foo foo = new DefaultFoo("sari");
        foo.printName(); // Foo 인터페이스의 추상메서드 사용
        foo.printNameUpperCase(); // Foo 인터페이스의 default 메소드 사용

        Foo.printAnything(); // Foo 인터페이스의 static method 사용

    }

}
