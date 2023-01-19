package com.study.modernjavainaction.etc.parallel;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Parallel {

    public static void main(String[] args) {

        /**
         * Arrays.parallelSort()
         *   - Fork/Join 프레임워크를 사용해서 배열을 병렬로 정렬하는 기능을 제공한다.
         */

        /**
         * 병렬 정렬 알고리즘
         *   - 배열을 둘로 계속 쪼갠다
         *   - 합치면서 정렬한다
         */

        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt()); // 1500개의 배열을 돌면서 랜덤한 값으로 채워줌

        long start = System.nanoTime(); // 시작시간 재고
        Arrays.sort(numbers); // sort정렬 (thread를 하나만씀)
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers); // parallelSort정렬 (쪼개고 쪼개고 정렬하고 합치고 합치고 PorkJoin Framework에서 여러쓰레드를 분산해서 처리)
        System.out.println("parallel soring took " + (System.nanoTime() - start));

    }

}
