package com.shares.business.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.shares.business.test.ConcurrentUtils.stop;

/**
 * @author wangmn
 * @description
 * @date 2018/1/25 10:43
 */
public class ConcurrentMapTest {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(1000);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(atomicInt::decrementAndGet));

        stop(executor);

        System.out.println(atomicInt.get());    // => 1000

    }
}
