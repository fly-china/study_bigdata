package com.lpf.jol.singleton;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lipengfei
 * @create 2020-07-01 22:49
 **/
public class TestSingle {


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = getThreadPool();
//        threadPool.prestartAllCoreThreads();

        for (int i = 0; i < 10000; i++) {
            threadPool.submit(() -> {
//                DcSingleton.getInstance().getValue();
            });
        }

        Thread.sleep(2000);

    }


    public static ThreadPoolExecutor getThreadPool() {
        return new ThreadPoolExecutor(10, 30,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10000),
                new ThreadFactory() {
                    private AtomicInteger threadIndex = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "LPF_Executor_" + this.threadIndex.incrementAndGet());
                    }
                });
    }
}
