package com.study.modernjavainaction.interfaceMethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class Iterator {

    public static void main(String[] args) {

        /**
         * 인터페이스의 기본 메서드(default method)와 스태틱 메서드
         */

        List<String> names = new ArrayList<>();
        names.add("sari");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        /**
         * forEach()
         *   - @FunctinalInterface 중에 consumer가 들어옴 T타입을 인자로 받고 void인 메서드
         */
        names.forEach(System.out::println); // 메서드 레퍼런스

        /**
         * spliterator()
         *   - 쪼갤 수 있는 기능을 가진 iterator라고 생각하면 됨
         *   - iterator는 hasNext로 다음 타입이 존재하는지 확인할 수 있는데 spliterator는 tryAdvance()로 확인 가능
         *   - 인자로 consumer를 받는다
         *   - spliterator를 trySplit()로 쪼개게되면 원래 spliterator는 절반의 뒷쪽 trySplit()의 메소드를 받은 spliterator는 앞쪽 절반을 가지게 됨
         */
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> stringSpliterator = spliterator.trySplit();
        System.out.println("==========================================");
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("==========================================");
        while (stringSpliterator.tryAdvance(System.out::println));
        System.out.println("===========================================");

        /**
         * stream()
         *   - 콜렉션의 기본 메서드(default method)
         *   - 엘리먼트들을 stream으로 만들어서 functional 하게 처리할 때 사용
         *   - stream() 기본적으로 spliterator으로 쪼개서 사용
         */
        long count = names.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("S"))
                .count();
        System.out.println("count = " + count);
        List<String> strings = names.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("S"))
                .collect(Collectors.toList());
        strings.forEach(System.out::println);
        System.out.println("===========================");

        /**
         * removeIf()
         *   - predicate에 해당하는것을 넘겨주면 됨
         */
        names.removeIf(s -> s.startsWith("s")); // s로 시작하는 string을 뺴라
        names.forEach(System.out::println);
        System.out.println("===========================");

        /**
         * comparator
         *   - 주로 정렬할 때 사용
         *   - @FunctionalInterface
         *   - 기본 메서드
         */
        names.sort(String::compareToIgnoreCase); // sort할 때 comparator을 넘겨줌, 문자열로 정렬\
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed()); // 반대로 정렬
        names.sort(compareToIgnoreCase.reversed().thenComparing(String::compareTo)); // 추가적으로 어떠한 정렬을 하고 싶다 하면 thenComparing() 사용
        System.out.println("===========================");

    }

}
