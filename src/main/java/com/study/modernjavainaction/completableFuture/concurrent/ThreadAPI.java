package com.study.modernjavainaction.completableFuture.concurrent;

public class ThreadAPI {

    public static void main(String[] args) throws InterruptedException {

        /**
         * Thread.sleep()
         *   - 쓰레드 동결
         *   - InterruptedException은 자고 있는 Thread를 누가 깨우면 발생
         */

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000L); // sleep()을 쓸경우 다른 쓰레드한테 서버 리소스를 넘김, 다른 쓰레드에게 먼저 일을 시킨다
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());

        /**
         * Thread.interrupt()
         * Thread 깨우기
         * 지금 람다식으로 사용하고 있는 Runnable()의 run()은 void 타입임
         * void 타입에서 return;을 쓰게 되면 종료
         * interrupt()는 쓰레드를 종료 시키는것이 아니라 깨우는 것이다
         * 밑 예제에서는 깨워서 InterruptedException() 발생시키기
         * Thread를 종료를 시킬려면 별다른 메서드가 있는것은 아니고 코드로 종료를 시켜줘야됨
         * 밑 예제에서는 return;으로 종료
         */
        Thread thread1 = new Thread(() -> {
            while (true) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("interrupted!");
                    return;
                }
            }
        });
        thread1.start();
        Thread.sleep(3000L);
        thread1.interrupt(); // 쓰레드 깨우기

        /**
         * Thread.join() - 다른 쓰레드 기다리기
         * join()도 결국은 기다리는 것이기 때문에 누가 이 쓰레드를 건드리게 되면 InterruptedException이 발생
         *
         */
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread2.start();
        try {
            thread2.join(); // 기다리고 나서 밑 코드 실행
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread2 + "is finished!"); // 위 join()이 있기 때문에 thread2 쓰레드가 끝나고 실행

    }

}
