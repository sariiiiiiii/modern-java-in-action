package com.study.modernjavainaction.etc.annotation;

import java.util.Arrays;

@Chicken("양념")
@Chicken("마늘간장")
public class AnnotationExample {

    class FeelsLikeChicken<@Chicken("양념") T> { // ElementType.TYPE_PARAMETER
        // <C> = 타입 파라미터,  C = 타입
        public <@Chicken("양념") C> void print(@Chicken("양념") C c) throws @Chicken("양념") RuntimeException {

        }
    }

    public static void main(String[] args) {

        Chicken[] chickens = AnnotationExample.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });

        // container를 이용해서 value값 가져오기
        ChickenContainer chickenContainer = AnnotationExample.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println("c.value() = " + c.value());
        });

    }

}
