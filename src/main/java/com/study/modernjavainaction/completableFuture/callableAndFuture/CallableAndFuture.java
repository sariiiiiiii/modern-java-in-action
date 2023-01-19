package com.study.modernjavainaction.completableFuture.callableAndFuture;

public class CallableAndFuture {

    /**
     * Callable
     *   - Runnable과 유사하지만 작업의 결과를 받을 수 있다
     *   - Runnable.run()은 void 타입이라 결과를 받을 수 없음
     */

    /**
     * Future
     *   - 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수 있다
     */

    /**
     * 결과 가져오기 Future.get()
     *   - 블록킹 콜이다
     *   - 타임아웃(최대한으로 기다릴 시간)을 설정할 수 있다
     */

    /**
     * 작업 ㅅ아태 확인하기 Future.isDone()
     *   - 완료 했으면 true 아니면 false를 리턴한다
     */

    /**
     * 작업 취소하기 Future.cancel()
     *   - 취소 했으면 true 못했으면 false를 리턴한다
     *   - parameter로 true를 전달하면 현재 진행중인 쓰레드를 interrupt하고 그러지 않으면 현재 진행중인 작업이 끝날때까지 기다린다
     */

    /**
     * 여러작업 동시에 실행하기 Future.invokeAll()
     *   - 동시에 실행한 작업 중에 제일 오래 걸리는 작업 만큼 시간이 걸린다
     */

    /**
     * 여러 작업 중에 하나라도 먼저 응답이 오면 끝내기 invokeAny()
     *   - 동시에 실행한 작업 중에 제일 짧게 걸리는 작업 만큼 시간이 걸린다
     *   - 블록킹 콜이다
     */

}
