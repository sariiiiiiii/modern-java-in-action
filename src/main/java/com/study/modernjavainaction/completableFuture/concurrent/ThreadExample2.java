package com.study.modernjavainaction.completableFuture.concurrent;

public class ThreadExample2 {

    public static void main(String[] args) {

        // Thread 만들기 2번째 방법
        // 람다표현식 활용

        // 익명클래스 활용
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread: " + Thread.currentThread().getName());
            }
        });
        thread.start();

        // 람다 표현식 활용
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread1: " + Thread.currentThread().getName());
        });
        thread1.start();

    }

}
