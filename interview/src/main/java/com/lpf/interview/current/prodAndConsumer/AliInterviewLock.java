package com.lpf.interview.current.prodAndConsumer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程顺序循环输出10次(Lock实现)
 * 要求：
 * 1、三个线程：ThreadA、ThreadB、ThreadC分别输出a,b,c
 * 2、按照ThreadA->a、ThreadB->b、ThreadC->c顺序循环输出10次
 *
 * @author lipengfei
 * @create 2019-02-13 13:58
 **/
public class AliInterviewLock {

    // 默认：非公平锁
    public static ReentrantLock GLOBAL_LOCK = new ReentrantLock();

    // 循环次数
    private static final Integer LOOP_NUMS = 10;

    // 线程名称（输出对应字符串）
    public static String[] CHARS = {"a", "b", "c"};

    // 线程个数
    private static final Integer THREAD_NUMS = CHARS.length;

    // 总输出次数
    public static Integer SUM_OUT_COUNT = LOOP_NUMS * THREAD_NUMS;

    // 当前循环次数
    public static Integer CURRENT_LOOP_NUM = 0;


    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,3,20, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
        threadPoolExecutor.execute(new ThreadDemo("a"));
        threadPoolExecutor.execute(new ThreadDemo("b"));
        threadPoolExecutor.execute(new ThreadDemo("c"));
        threadPoolExecutor.shutdown();


    }
}

class ThreadDemo implements Runnable {

    private String threadName;

    public ThreadDemo(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {

        // 当前输出次数 < 目标循环次数
        while (AliInterviewLock.CURRENT_LOOP_NUM < AliInterviewLock.SUM_OUT_COUNT) {

            try {
                // 获取锁
                AliInterviewLock.GLOBAL_LOCK.lock();


                while (AliInterviewLock.CURRENT_LOOP_NUM < AliInterviewLock.SUM_OUT_COUNT
                        && this.threadName.equals(AliInterviewLock.CHARS[AliInterviewLock.CURRENT_LOOP_NUM % AliInterviewLock.CHARS.length])
                ) {

                    //第几轮循环
                    int bigLoopNum = (AliInterviewLock.CURRENT_LOOP_NUM / AliInterviewLock.CHARS.length) + 1;

                    System.out.println("第" + bigLoopNum + "次Thread" + threadName.toUpperCase() + "->" + threadName);

                    // 当前循环次数+1
                    AliInterviewLock.CURRENT_LOOP_NUM++;
                }


            } finally {
                // 如果持有锁，则释放锁
                if (AliInterviewLock.GLOBAL_LOCK.isLocked()) {
                    AliInterviewLock.GLOBAL_LOCK.unlock();
                }
            }

        }

    }
}
