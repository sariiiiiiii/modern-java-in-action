package com.study.modernjavainaction.completableFuture.concurrent;

public class ThreadExample1 {

    public static void main(String[] args) {

        /**
         * Thread의 순서는 보장을 못한다
         * 밑 예제를 보면 순서상 myThread.start()를 먼저 했기 때문에 Thread 출력후 Hello가 나올 거 같지만
         * 간혹, Hello가 먼저 출력이 되는것을 볼 수 있다
         */

        System.out.println(Thread.currentThread().getName()); // main (main thread)


        MyThread myThread = new MyThread();
        myThread.start(); // thread 실행

        System.out.println("Hello: " + Thread.currentThread().getName());

    }

    // main thread 에서 다른 thread 만들기
    // 첫 번째 방법
    // Thread 상속받기
    static class MyThread extends Thread {
        @Override
        public void run () {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }

}
