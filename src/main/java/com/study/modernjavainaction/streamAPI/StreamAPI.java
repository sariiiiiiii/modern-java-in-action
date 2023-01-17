package com.study.modernjavainaction.streamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {

    public static void main(String[] args) {

        /**
         * 스트림 API는 우리가 가지고 있는 어떤 컬렉션이나 또는 우리가 직접 스트림을 만들 수 있는데
         * 연속된 데이터를 처리하는 오퍼레이션의 모음이라고 생각하면 좋다
         * 그 자체가 데이터가 아님
         */

        /**
         * 스트림의 메서드는 크게 2가지로 나눌 수 있다
         * 중개 오퍼레이션과 종료 오퍼레이션인데 중개 오퍼레이션은 계속해서 이어나갈 수 있고 종료 오퍼레이션은 그 상태로 종료시킨다
         * 중개 오퍼레이션은 stream()을 리턴하고 종료 오퍼레이션은 다른 타입을 리턴
         * 종료 오퍼레이션이 실행되기 전까지는 중개형 오퍼레이션은 무의미함, 실행이 되지 않음 (그냥 정의만 한것)
         */

        List<String> names = new ArrayList<>();
        names.add("sari");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        names.stream().map(s -> {
            System.out.println(s); // 출력 안됨 why ? .map() 중개 오퍼레이션 상태에서 끝냈기 때문에 stream을 return하기 때문
            return s.toUpperCase();
        });
        System.out.println("==================================================");
        // names의 데이터들을 대문자로 바꿔서 새로운 컬렉션을 만드는거지 기본데이터들을 바꾸는 것이 아님
        names.forEach(System.out::println);
        Stream<String> toUpperCases = names.stream().map(String::toUpperCase);
        toUpperCases.forEach(System.out::println);
        System.out.println("==================================================");

        /**
         * parallelStream()
         *   - parallelStream()을 이용하면 뒤에 쭉쭉 주는 오퍼레이터들이 병렬적으로 처리된 후 나중에 모아줌 (collect 같은것을 쓰게 되면)
         *   - parallelStream()은 Thread.currentThread().getName()을 확인해보면 서로 다른 thread를 사용한다 (멀티 쓰레드)
         *   - 그렇다고 멀티쓰레드가 꼭 빠른것은 아님 (쓰레드를 생성할 떄는 그에 대한 비용이 들기 때문)
         *   - parallelStream()이 유용할 때는 데이터가 정말 방대할 경우
         *   - https://girawhale.tistory.com/131
         */
        List<String> collect = names.parallelStream().map(s -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }

}
