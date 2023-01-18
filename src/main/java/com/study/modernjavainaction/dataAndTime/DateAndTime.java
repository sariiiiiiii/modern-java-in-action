package com.study.modernjavainaction.dataAndTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateAndTime {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 자바 8에 새로운 날짜와 시간 API가 생긴 이유
         *   - 그전까지 사용하던 java.util.Date 클래스는 mutable 하기 때문에 thread safe하지 않다 (한 인스턴스의 시간을 변경할 수 있다)
         *   - 클래스 이름이 명확하지 않다 (Date인데 시간까지 다룬다)
         *   - 버그 발생할 여지가 많다 (타입 안정성이 없고, 월이 0부터 시작한다거나...)
         *   - 날짜 시간 처리가 복잡한 애플리케이션에서는 보통 Joda Time을 쓰곤 했다
         */

        /**
         * 자바 8 이전의 Date 관련 type
         * Date()의 단점 : 작명이 Date 지만 사실 시간까지도 나타낼 수 있고, 타임스탬프도 표현할 수 있다 (근본적으로 타임스탬프)
         *   - long time = date.getTime() Date인데 시간을 가져온다고 ? (몇 시, 몇 분 할때 시간이 아니라 아 몰라 ~~)
         */

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();



    }

}
