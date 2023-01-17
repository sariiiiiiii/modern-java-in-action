package com.study.modernjavainaction.streamAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIExample {

    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(o -> o.getTitle().startsWith("spring"))
                .forEach(o -> System.out.println(o.getId()));

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
//                .filter(o -> !o.isClosed())
                .filter(Predicate.not(OnlineClass::isClosed)) // Predicate의 static method와 메서드 레퍼런스 활용 (메서드 레퍼런스의 임의로 !, not을 붙여줄 수 없으니 Predicate interface 활용)
                .forEach(o -> System.out.println(o.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> sariEvents = new ArrayList<>();
        sariEvents.add(springClasses);
        sariEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // List안에 OnlineClass를 담고있는 List가 또 하나 들어있다
        // 이럴떄는 stream() 메서드중 중개 오퍼레이션인 stream().flatMap()을 활용하면 위를 예제로 들면 첫 번째 리스트에서는 4개의 OnlineClass, 두 번째 리스트에서는 3개의 OnlineClass를 쫙 뽑는다
        sariEvents.stream()
//                        .flatMap(list -> list.stream())
                .flatMap(Collection::stream) // 메서드 레퍼런스
//                .map(OnlineClass::getId)
//                .forEach(System.out::println);
                .forEach(o -> System.out.println(o.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);


        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream()
                //      .allMatch() // 모든것 중에
                .anyMatch(o -> o.getTitle().contains("Test"));// 그중에 어떤것이라도
        System.out.println("test = " + test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> spring = springClasses.stream()
                .map(OnlineClass::getTitle)
                .filter(title -> title.contains("spring"))
                .collect(Collectors.toList());
        spring.forEach(System.out::println);

    }

}
