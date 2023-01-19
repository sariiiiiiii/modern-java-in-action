package com.study.modernjavainaction.completableFuture.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * CompletableFuture은 Executor와 달리 별도의 쓰레드풀을 만들지도 않았는데 어떻게 비동기적으로 실행이 되는걸까 ?
         * 바로 Thread.currentThread().getName()을 해보면 ForkJoinPool이 출력이 되는것을 볼 수 있다
         * ForkJoinPool도 Executor을 구현한 구현체중의 하나인데 다른점은 그냥 queue(선입선출)를 쓰는것이 아니라 이거 인터넷에서 찾아보자 좀 어려움
         */

        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("sari"); // future의 기본값 설정
        CompletableFuture<String> future1 = CompletableFuture.completedFuture("sari"); // 스태틱 팩토리 메서드를 활용해서 이렇게도 사용 가능

        System.out.println(future.get());
        System.out.println(future1.get());

        // return이 없는 경우 future정의 (정의만 한것뿐이기 때문에 get()을 해야지 실행이됨)
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
        });
        future2.get();

        // return타입이 있는 경우
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
            return "hello";
        });
        future3.get();

        // thenApply()를 이용해서 return값으로 콜백함수
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello4 " + Thread.currentThread().getName());
            return "hello";
        }).thenApply(String::toUpperCase); // return 받은 값을 대문자로 변경 thenApply() 활용
        System.out.println(future4.get()); // HELLO

        // thenAccept()를 이용해서 콜백함수 처리 (return이 없고 받아서 쓰기만 할 경우)
        CompletableFuture<Void> future5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello5 " + Thread.currentThread().getName());
            return "hello";
        }).thenAccept(System.out::println);//
        System.out.println(future5.get()); // consumer라 return 없기 때문에 null값

        // thenRun() return 값을 받지도 않고 무언가를 하기만 하면 된다
        CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello5 " + Thread.currentThread().getName());
            return "hello";
        }).thenRun(() -> {
           // 이 자리에는 Runnable이 오기 때문에 return을 받지도 않음
            System.out.println("finished!");
        });
        future6.get();

        // ThreadPool을 만들어서 사용하기
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future7 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello7 " + Thread.currentThread().getName()); // ForkJoinPool.commonPool이 출력되는것이 아니라 pool-thread-1 출력
            return "hello";
        }, executorService).thenRun(() -> {
            System.out.println("finished!");
        });
        future7.get();

        executorService.shutdown();

    }

}
