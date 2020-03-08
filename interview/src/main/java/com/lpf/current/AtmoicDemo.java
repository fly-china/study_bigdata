package com.lpf.current;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author lipengfei
 * @create 2020-02-08 21:19
 **/
public class AtmoicDemo {

    public static ExecutorService THREAD_POOL = Executors.newFixedThreadPool(5);
    public static volatile Integer sum = 0;
    public static AtomicInteger ATOMIC_SUM = new AtomicInteger(0);

    @Test
    public void testUnSafeAdd() throws Exception {
        System.out.println("测试开始");

        for (int j = 0; j < 1000; j++) {
            THREAD_POOL.submit(() -> {
                sum++;
            });
        }
        Thread.sleep(2000);
        System.out.println("sum = " + sum);
    }

    @Test
    public void testAtomicAdd() throws Exception {
        System.out.println("测试开始");

        for (int j = 0; j < 1000; j++) {
            THREAD_POOL.submit(() -> {
                ATOMIC_SUM.getAndIncrement();
            });
        }
        Thread.sleep(5000);
        System.out.println("ATOMIC_SUM = " + ATOMIC_SUM);
    }

    @Test
    public void testAtomicObjectAdd() throws Exception {
        System.out.println("测试开始");
        AtomicIntegerFieldUpdater<CounterObject> updater = AtomicIntegerFieldUpdater
                .newUpdater(CounterObject.class, "count");
        CounterObject countObj = new CounterObject();

        for (int j = 0; j < 1000; j++) {
            THREAD_POOL.submit(() -> {
                updater.getAndIncrement(countObj);
            });
        }
        Thread.sleep(5000);
        System.out.println("CounterObject对象中的count = " + countObj.getCount());
    }


    public class CounterObject {
        public volatile int count = 0;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
