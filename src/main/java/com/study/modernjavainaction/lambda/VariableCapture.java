package com.study.modernjavainaction.lambda;

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

        IntConsumer printInt = (i) -> System.out.println(i);

    }

}
