package com.study.modernjavainaction.lambda;

@FunctionalInterface
public interface RunSomething {

    /**
     * 추상메서드가 하나만 있으면 함수형 인터페이스
     * 인터페이스에서는 abstract 생략 => (abstract) void doIt();
     * @FunctionalInterface => java library annotation 추상메서드가 1개 초과하면 컴파일시 error
     * 함수형 인터페이스를 정의한 경우에는 FunctionalInterface annotation을 사용하면 좋다
     *
     * JAVA8부터는 static method와 default method를 사용할 수 있음
     */

    void doIt();

    static void printName() {
        System.out.println("sari");
    }

    default void printAge() {
        System.out.println("40");
    }

    // void doItAgain(); 2개가 있으면 X => @FunciontalInterface annotation에 오류발생

}
