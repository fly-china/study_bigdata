package com.lpf.interview.current.prodAndConsumer;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 三个线程顺序循环输出10次(Lock实现)
 * 要求：
 * 1、三个线程：ThreadA、ThreadB、ThreadC分别输出a,b,c
 * 2、按照ThreadA->a、ThreadB->b、ThreadC->c顺序循环输出10次
 *
 * @author lipengfei
 * @create 2019-02-15 16:35
 **/
public class AliInterviewSingleExecutor {

    @Test
    public void test(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        int loopNums =10;

        for (int i = 0; i < loopNums; i++) {
           executorService.submit(new ThreadA());
           executorService.submit(new ThreadB());
           executorService.submit(new ThreadC());
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ThreadA implements Runnable {
        @Override
        public void run() {
                System.out.println("ThreadA->a");
        }
    }
    class ThreadB implements Runnable {
        @Override
        public void run() {
            System.out.println("ThreadB->b");
        }
    }
    class ThreadC implements Runnable {
        @Override
        public void run() {
            System.out.println("ThreadC->c");
        }
    }
}

