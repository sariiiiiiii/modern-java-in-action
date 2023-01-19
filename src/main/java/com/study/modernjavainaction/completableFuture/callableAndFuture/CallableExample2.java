package com.study.modernjavainaction.completableFuture.callableAndFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // TODO: ExecutorService executorService = Executors.newSingleThreadExecutor(); 를 맞춘뒤 shutdown()을 하고 invokeAny를 쓰려고 다시 사용해봤는데 왜 오류가 날까...

        /**
         * Future.invokeAll()은
         * 밑 예제처럼 2초, 3초, 1초 씩 기다리는 코드 있는데 끝날때마다 출력이 되는게 아니라 다 끝날떄까지 기다림
         * 즉, 제일 오래 기다려야 하는 boot 가 끝날때까지 기다리고 그 다음에 3개의 result를 가져올 수 있음
         */

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "hello";
        };

        Callable<String> boot = () -> {
            Thread.sleep(3000L);
            return "boot";
        };

        Callable<String> sari = () -> {
            Thread.sleep(1000L);
            return "sari";
        };

        // Callable를 list로 한번에 묶기 invokeAll(Collection)
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, boot, sari));
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        executorService.shutdown();

        /**
         * invokeAny()는 동시에 실행한 작업 중에 제일 짧게 걸리는 작업 만큼 시간이 걸린다
         * 블록킹 콜
         */
        ExecutorService executorService2 = Executors.newFixedThreadPool(3); // newFixedThreadPool를 2개로 해보니까 먼저 sari2가 출력이 되는게 아니라 hello2가 출력 sari2가 못들어감

        Callable<String> hello2 = () -> {
            Thread.sleep(2000L);
            return "hello2";
        };

        Callable<String> boot2 = () -> {
            Thread.sleep(3000L);
            return "boot2";
        };

        Callable<String> sari2 = () -> {
            Thread.sleep(1000L);
            return "sari2";
        };

        // invokeAny는 블록킹 콜이다 반환값은 future로 받는것이 아니라 해당 Callable 리턴타입으로 바로 받음
        String s = executorService2.invokeAny(Arrays.asList(hello2, boot2, sari2));
        System.out.println(s); // 1초 기다렸다가 sari2 -> 가장 먼저 끝나는 Callable을 반환한다... 멀티쓰레드 어렵다...

        executorService2.shutdown();

    }

}
