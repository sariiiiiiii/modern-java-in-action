package com.study.modernjavainaction.etc.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // Runtime이 실행될때 동안
//@Target(ElementType.TYPE_PARAMETER) // 어노테이션을 사용할 곳 (타입 파라미터)
@Target(ElementType.TYPE_USE) // 타입 파라미터를 포함해서 타입을 선언하는 모든 곳
@Repeatable(ChickenContainer.class)
public @interface Chicken {

     String value();

}
