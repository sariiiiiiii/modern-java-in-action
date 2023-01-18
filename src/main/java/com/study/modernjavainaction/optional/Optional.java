package com.study.modernjavainaction.optional;

public class Optional {

    public static void main(String[] args) {

        /**
         * 메소드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법
         *   - 예외를 던진다 (비싸다, 스택트레이스를 찍어두니까)
         *   - null을 리턴한다 (비용 문제가 없지만 그 코드를 사용하는 클라이언트 코드가 주의해야 한다)
         *   - Optional을 리턴한다 (클라이언트에 코드에게 명시적으로 빈 값일 수도 있다는 걸 알려주고, 빈 값인 경우에 대한 처리를 강제한다)
         */

        /**
         * Optional
         *   - 오직 값 한 개가 들어있을 수도 없을 수도 있는 컨테이너
         */

        /**
         * 주의할 것
         *   - 리턴값으로만 쓰기를 권장한다 (메소드 매개변수 타입, 맵의 키 타입(맵의 키는 절대 null일 수 없음), 인스턴스 필드 타입으로 쓰지 말자)
         *   - Optional을 리턴하는 메소드에서 null을 리턴하지 말자
         *   - 프리미티브 타입용 Optional을 따로 있다. OptionalInt, OptionalLong...
         *     - Optional.of(10)을 넣게 되면 내부적으로 박싱, 언박싱이 이루어짐 그래서, 성능에 좋지 않다
         *     - 좋은 예시) OptionalInt.of(10)
         *   - Collection, Map, Stream, Array, Optional은 Optional로 감싸지 말것.
         *     - 그 자체로 이미 비어있는지 없는지 판단할 수 있는 컨테이너 성격의 인스턴스들은 Optional로 감싸면 두 번 감싸는 것
         */

    }

}
