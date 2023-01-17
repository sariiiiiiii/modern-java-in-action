package com.study.modernjavainaction.staticOrdefault;

public interface Todo {

    default void printNameUpperCase() {
        System.out.println("SARI");
    }

}
