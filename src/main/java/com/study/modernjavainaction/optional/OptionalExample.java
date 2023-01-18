package com.study.modernjavainaction.optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {

        /**
         * Optional.ofNullable = null 일수도 있다
         * Optional.of = 무조건 null이 아니다 (null일시 NPE 발생)
         */

        List<OfflineClass> springClasses = new ArrayList<>();
        springClasses.add(new OfflineClass(1, "spring boot", true));
        springClasses.add(new OfflineClass(2, "spring data jpa", true));
        springClasses.add(new OfflineClass(3, "spring mvc", false));
        springClasses.add(new OfflineClass(4, "spring core", false));
        springClasses.add(new OfflineClass(5, "rest api development", false));

        OfflineClass spring_boot = new OfflineClass(1, "spring boot", true);
        Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        System.out.println("studyDuration = " + studyDuration); // NPE 발생 why ? progress가 null 이기 때문 null을 참조해서 무언가를 할 수 없기 때문

        // 요런식으로 코딩을 해옴.
        // 1. 에러를 만들기 굉장히 좋은 코드 (null을 check를 하는것을 깜빡할 수 있기 때문)
        // 2. null을 return 하는 거 자체가 문제임 ex) getProgress()
        Progress progress = spring_boot.getProgress();
        if (progress != null) {
            System.out.println(progress.getStudyDuration());
        }



    }

}
