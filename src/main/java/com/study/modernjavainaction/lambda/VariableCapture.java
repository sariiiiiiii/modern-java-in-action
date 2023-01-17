package com.study.modernjavainaction.lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class VariableCapture {

    public static void main(String[] args) {

        /**
         * 변수 캡쳐 (Variable Capture)
         * 로컬 변수 캡처
         *   - final이거나 effective final인 경우에만 참조할 수 있다
         *   - 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일이 방지함
         *
         * effective final
         *   - 이것도 역시 자바 8부터 지원하는 기능으로 "사실상" final인 변수
         *   - final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다
         *
         * 익명 클래스 구현체와 달리 "쉐도잉" 하지 않는다
         *   - 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다
         */

        VariableCapture variableCapture = new VariableCapture();
        variableCapture.run();

    }

    private void run() {
//        final int baseNumber = 10; // LocalVariable 자바 8 버전 이전에는 final을 붙여줬어야 함
        int baseNumber = 10; // LocalVariable 자바 8 이후부터는 final을 생략할 수 있다. (생략조건 : LocalVariable 변수가 사실상 final인 경우, 어디서도 변경이 안된 경우) = effective final

        /**
         * 로컬클래스, 익명클래스, 람다에서 LocalVariable 참조
         * {} => 중괄호 스코프 밖에 있는 값을 참조
         *
         * 로컬클래스, 익명클래스와 람다클래스의 공통점은 LocalVariable 변수가 사실상 final인 경우(effective final)는 셋다 모두 참조할 수 있다
         * 차이점은 로컬클래스와 익명클래스는 쉐도윙이 되지만 람다는 쉐도윙이 되지 않는다
         *   - 로컬클래스와 익명클래스는 별도의 스콥이기 때문 run()이라는 method 안에 또다른 스콥
         *   - 람다는 사실상 람다를 감싸고 있는 method랑 같다 = 쉐도윙이 일어나지 않는다
         */

        // 로컬클래스에서 LocalVariable 참조
        class LocalClass {
            void printNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // =11 why? LocalClass 스콥이 밖에있는 스콥을 가렸음
            }
        }

        // 익명클래스에서 LocalVariable 참조
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) { // =11 why? 파라미터로 들어온 baseNumber라는 이름이 LocalVariable를 가렸음
                System.out.println(baseNumber);
            }
        };

        // 람다에서 LocalVariable 참조
        // baseNumber를 파라미터 변수로 받으면 컴파일 에러가 뜸
        //   -- why? 람다는 람다를 감싸고 있는 메소드와 같은 스콥이기 때문에 같은 메소드안에서 같은 변수이름을 쓸수 있나? = 없다
//        IntConsumer printInt = (baseNumber) -> {
        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber);
        };

        integerConsumer.accept(1);
        printInt.accept(1);
//        baseNumber++; effective final 변수를 값을 변경을 해주게 되면 컴파일 에러 발생 (더이상 참조해서 사용할 수 없음)

    }

}
