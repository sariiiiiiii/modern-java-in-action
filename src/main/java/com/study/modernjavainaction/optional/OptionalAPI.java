package com.study.modernjavainaction.optional;

public class OptionalAPI {

    public static void main(String[] args) {

        /**
         * Optional 만들기
         *   - Optional.of()
         *   - Optional.ofNullable()
         *   - Optional.empty()
         */

        /**
         * Optional에 값이 있는지 없는지 확인하기
         *   - isPresent()
         *   - isEmpty() (Java 11부터 제공)
         */

        /**
         * Optional에 있는 값 가져오기
         *   - get()
         *   - 만약 비어있는 Optional에서 무언가를 꺼낸다면??
         */

        /**
         * Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라
         *   - orElse(T)
         *   - 예) JPA로 시작하는 수업이 없다면 비어있는 수업을 리턴하라
         */

        /**
         * Optional에 값이 있으면 가져오고 없는 경우에 ~~ 하라
         *   - orElseGet(Supplier)
         *   - 예) JPA로 시작하는 수업이 없다면 새로 만들어서 리턴하라
         */

        /**
         * Optional에 값이 있으면 가져오고 없는 경우 에러를 던져라
         *   - orElseThrow()
         */

        /**
         * Optional에 들어있는 값 걸러내기
         *   - Optional filter(Predicate)
         */

        /**
         * Optional에 들어있는 값 변환하기
         *   - Optional map(Function)
         *    - Optional flatMap(Function): Optional 안에 들어있는 인스턴스가 Optional인 경우에 사용하면 편리하다
         */

    }

}
