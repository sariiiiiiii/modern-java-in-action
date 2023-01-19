package com.study.modernjavainaction.completableFuture.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample {

    public static void main(String[] args) {

        // Executor를 상속받은 인터페이스
//        ExecutorService executorService = new ExecutorService() {
//        }

        // ExecutorService를 상속받은 인터페이스
//        ScheduledExecutorService service = new ScheduledExecutorService() {
//        }

        /**
         * ExecutorService는 작업이 실행되고 나서 다음 작업이 실행될때까지 계속 기다리기 때문에 프로세스가 죽지 않음
         * 그래서 작업 처리한다음에 명시적으로 shutdown()을 해줘야 함
         */

        // thread를 하나만 사용하는 executor
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 사용예제 1 (execute)
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        // 사용예제 2 (submit)
        executorService.submit(() -> System.out.println("Thread: " + Thread.currentThread().getName()));
        executorService.shutdown(); // graceful shutdown을 하게 됨 끝까지 작업을 맞치고 종료하게 됨(우아하게 죽인다, 아름답게 죽인다)
        executorService.shutdownNow(); // 노 게런티, 돌고 있는 쓰레드가 다 종료되고 끝낼지 안끝낼지 모르는거

        /**
         * 쓰레드는 2갠데 작업은 5개가 있다
         * 그래도 실행은 된다 2개의 쓰레드로 작업을 나눠서 함
         * 어떻게 처리되는것인가 ?
         * 작업은 5갠데 쓰레드풀은 2개다 2개의 쓰레드풀로 5개의 작업을 처리해야 하니 쓰레드는 바쁘다
         * 그래서 그 전에 blocking queue라는게 있어서 거기다가 보낸 태스크를 해야될 작업들을 쌓아놓는다
         * 작업이 끝나면 비어있는 쓰레드는 큐에서 가져와 작업을 실행
         * 적은 쓰레드풀로 하기 떄문에 비용이 덜 드는 장점이 있다
         */
        ExecutorService executorService1 = Executors.newFixedThreadPool(2); // ThreadPool 2개 생성
        executorService1.submit(getRunnable("Hello"));
        executorService1.submit(getRunnable("Sari"));
        executorService1.submit(getRunnable("The"));
        executorService1.submit(getRunnable("Jave"));
        executorService1.submit(getRunnable("Thread"));
        executorService1.shutdown();

        /**
         * ScheduledExecutor 활용
         */
        ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
        executorService2.schedule(getRunnable("Spring"), 3, TimeUnit.SECONDS); // 3초 있다가 실행
        executorService2.scheduleAtFixedRate(getRunnable("seconds"), 1, 2, TimeUnit.SECONDS); // 1초 기다렸다가 2초마다 실행, 이때는 shutdown()을 활용하면 InterreptException 발생
        executorService2.shutdown();

    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + ": " + Thread.currentThread().getName());
    }

}
