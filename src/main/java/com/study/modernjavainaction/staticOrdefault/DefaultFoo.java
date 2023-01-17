package com.study.modernjavainaction.staticOrdefault;

public class DefaultFoo implements Foo, Todo {

    /**
     * Foo 인터페이스와, todo 인터페이스의 같은 메소드의 기능이 있으면 그 두 인터페이스를 상속받는 클래스에서는 충돌이 일어난다 (컴파일에러)
     * 그 때는 직접 Overiding에서 구현해야 함
     */

    private final String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    // 재정의해서 사용
    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase());
    }

}
