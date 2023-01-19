package com.study.modernjavainaction.completableFuture.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
            return "hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("world " + Thread.currentThread().getName());
            return "world";
        });

        // 기존에는 get()을 해야지 쓸수 있었음
        hello.get();
        world.get();

        // thenCompose()를 활용해서 둘을 연결한 하나의 future 생성
        // future간의 의존성이 있는 경우 뭐 하나 끝내고 그 값으로 다른거를 실행시키기 위함
        CompletableFuture<String> future = hello.thenCompose(CompletableFutureExample2::getWorld);
        System.out.println(future.get());


        // combine() ex) 애플 주식도 가져오고 MS 주식도 동시에 가져오고 싶을 때
        // 둘의 연관관계가 없는 경우지만 둘이 동시에 비동기적으로 실행하는 방법
        CompletableFuture<String> future1 = hello.thenCombine(world, (h, w) -> {
            System.out.println("hello world " + Thread.currentThread().getName());
            return h + " " + w;
        });// hello의 결과랑 world의 결과가 둘다 왔을 때 그 두개의 값으로 뭔가를 할때는 Bifunction()
        System.out.println(future1.get()); // hello world

        // allOf() 값이 2개 이상일 때 여러 task들을 다 합쳐가지고 실행
        CompletableFuture<Void> future2 = CompletableFuture.allOf(hello, world)
                .thenAccept(result -> {
                    // 이게 참 애매함
                    // 모든 task들의 타입이 같은거라는 보장도 없고, 어느 한 task에서 예외가 났을수도 있을텐데..? 그래서 get() 했을 때 결과가 null 인건가... 그럴떄는 밑에 예제
                    System.out.println("result = " + result);
                });
        System.out.println(future2.get()); // null

        // 위에 예제에서 값을 제대로 받고 싶을 때 (기다렸다가 작업을 처리하는 것)
        List<CompletableFuture<String>> futureList = Arrays.asList(hello, world); // CompletableFuture를 list로 만들고
        CompletableFuture[] futuresArray = futureList.toArray(new CompletableFuture[futureList.size()]); // 배열로 만들고 (처음부터 array로 만들어도되고)

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> {
                    // thenApply 하는 시점에는 이미 모든 future의 작업이 끝났다
                    // 그 말은 즉슨, get(), join()을 사용할 수 있다는 말. get()말고 join()은 unCheckedException을 발생시키니까 join을 쓰자
                    return futureList.stream()
                            .map(f -> f.join())// 결과값 뽑기
                            .collect(Collectors.toList()); // 그 결과값을 뽑아서 리스트로 만들기
                });
        List<String> strings = results.get();
        results.get().forEach(System.out::println); // get()을 활용해서 list로 뽑기


        // anyOf() 둘중에 먼저 도착하는 결과로만 실행
        CompletableFuture<Void> future4 = CompletableFuture.anyOf(hello, world)
                .thenAccept(s -> {
                    System.out.println(s);
                });
        System.out.println(future4.get());

        // 예외처리 1 (exceptionally)
        boolean  throwError = true;
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            return "Hello";
        }).exceptionally(ex -> { // 예외처리
            // function을 넘겨줄 수 있음
            System.out.println(ex.getMessage());
            return "Error!";
        });
        System.out.println(exceptionally.get());


        // 예외처리 2 (handle)
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            return "Hello";
        }).handle((result, ex) -> { // 첫번째 인자는 error가 안났을 때 결과값, 두번째 인자는 error 났을 때 exception
            if (ex != null) {
                return "ERROR!";
            }
            return result;
        });
        System.out.println(handle.get());

    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("world " + Thread.currentThread().getName());
            return message + " world";
        });
    }

}
