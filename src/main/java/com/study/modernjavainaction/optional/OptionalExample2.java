package com.study.modernjavainaction.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalExample2 {

    public static void main(String[] args) {

        List<OfflineClass> springClasses = new ArrayList<>();
        springClasses.add(new OfflineClass(1, "spring boot", true));
        springClasses.add(new OfflineClass(5, "rest api development", false));

        Optional<OfflineClass> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = spring.isPresent();
        System.out.println("present = " + present); // true

        Optional<OfflineClass> jpa = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();
        boolean present1 = jpa.isPresent(); // false
        boolean empty = jpa.isEmpty(); // true (isPresent()의 반대경우)
        System.out.println("present1 = " + present1);
        System.out.println("empty = " + empty);

        OfflineClass offlineClass = spring.get();
        System.out.println("offlineClass = " + offlineClass.getTitle()); // get()으로 꺼냈을 때 값이 있으면 아무 문제 없는데, 비어있는 경우에 뭔가를 꺼낼려고 하면 runtimeException 발생 (NoSuchElementException)

        if (jpa.isPresent()) { // get()을 할 때에는 값의 유무 확인 필요, 하지만 이것도 if를 쓰지말고 Optional의 API 기능을 활용하자
            OfflineClass offlineClass1 = jpa.get();
        }

        spring.ifPresent(oc -> System.out.println(oc.getTitle())); // if를 활용하지 않고 람다식 활용 (consumer functionalInterface)

        OfflineClass offlineClass1 = spring.orElse(createNewClass()); // 없으면 새로운 객체 생성(메서드 레퍼런스나 람다 익스프레션이 실행되는것이 아니라 인스턴스 실행) /  하지만 orElse()는 있을때도 실행되고 없을때도 인스턴스가 실행됨

        OfflineClass offlineClass2 = jpa.orElseGet(() -> createNewClass()); // 없을시에만 실행 (supplier functionalInterface)

        OfflineClass offlineClass3 = jpa.orElseThrow(IllegalStateException::new); // 없으면 에러를 띄어주고 싶을 때(supplier functionalInterface) 기본적으로는 RunTimeException(NoSuchElementException 발생)

        Optional<OfflineClass> offlineClass4 = spring.filter(oc -> !oc.isClosed()); // 값이 있으면 객체가 담겨진 Optioanl이 반환되고 아니면 Optional.empty 반환

        // OfflineClass.getId()의 맞는 타입을 감싸고 있는 Optional 반환 없으면 Optional.empty
        // 만약 Optional이 감싸고 있는 객체가 Optional이다 Optional<Optional<String>> 일때는 Optional.flatMap() 활용 Optional의 껍질을 한번 까줌
        Optional<Integer> integer = spring.map(oc -> oc.getId());

    }

    private static OfflineClass createNewClass() {
        System.out.println("creating new online class");
        return new OfflineClass(10, "New class", false);
    }

}
