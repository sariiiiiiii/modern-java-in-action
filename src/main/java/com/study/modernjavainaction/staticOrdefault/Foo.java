package com.study.modernjavainaction.staticOrdefault;

public interface Foo {

    /**
     * 인터페이스의 있는 메소드는 모두 public
     * 인터페이스를 구현할 때 인스턴스들의 공통적으로 제공해주는 기능이 있었으면 좋겠다해서 만들어진 자바 8 기능
     * 해당 인터페이스를 구현하고 있는 모든 인스턴스의 추가적인 기능을 생기게 해줄수 있게 하는 기능
     *   - 편리하면서, 강력하면서 주의해야 하는 기능
     * default 메소드의 모든 기능이 제대로 동작할거라는 보장은 없다
     * 예를들면, 밑의 getName()에서 무슨 값이 올지는 모른다 (null이 온다거나 String이 아닌 값이 오게되면 runtimeException 발생)
     * 그 때, @implSpec 사용 (상세 구현 내용 설명)
     * 아니면 이 기능을 구현하는 쪽에서 재정의해서 사용하면 됨
     */

    /**
     * Object(hashCode(), equals(), toString())가 제공하는 기능은 기본 메소드로 제공할 수 없다.
     *   - 구현체가 재정의해야됨
     * default String toString() {
     *     = 컴파일 에러
     * }
     *
     * String toString(); 추상메서드로 만드는것은 가능 (이 인터페이스의 toString은 Object가 제공하는 toString()과는 다르다 할때... 사용하자)
     */

    /**
     * 스태틱 메소드
     * 해당 타입 관련 헬퍼 또는 유틸리티 메소드를 제공할 떄 인터페이스에 스태틱 메소드를 제공할 수 있음
     */

    void printName();

    // void pringInterger(); // 이 기능을 넣게 되면 GreetingInterface를 참조하고 있는 class들이 모두 컴파일 에러가 발생함 (추가한 추상메서드를 구현하지 않았기 때문에)
    // 그렇지 않기 위애 앞에 접근제어자 default를 붙이게 되면 추가적으로 사용할 수 있음
    /**
     * @implSpec
     * 이 구현제는 getName()으로 가져온 문자열을 대문자로 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    static void printAnything() {
        System.out.println("Foo");
    }

    String getName();

}
