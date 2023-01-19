package com.study.modernjavainaction.completableFuture.callableAndFuture;

import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "hello";
        };

        // Callable이 return 하는 타입의 Future를 받을 수 있음
        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone()); // 상태 확인 false
        System.out.println("Started!"); // 여기까지는 안기다리고 실행이 됨

        helloFuture.get(); // Future.get()을 만난 순간 결과값을 가져올때까지 기다린다 (블록킹 콜)

        helloFuture.cancel(true); // 현재 진행중인 작업을 interrupt 하면서 종료
        helloFuture.cancel(false); // false는 기다림, 하지만 기다렸다 한들 일단 cancel()를 하면 결과를 가져올 수 없음. Future.get() 해서 값을 가져올경우 오류 발생 (CancellationException 발생)

        System.out.println(helloFuture.isDone()); // 상태 확인 true
        System.out.println("End!");
        executorService.shutdown();

    }

}
