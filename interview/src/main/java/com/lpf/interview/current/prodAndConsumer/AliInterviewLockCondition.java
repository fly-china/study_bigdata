package com.lpf.interview.current.prodAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程顺序循环输出10次(Lock-Condition的实现)
 * 要求：
 * 1、三个线程：ThreadA、ThreadB、ThreadC分别输出a,b,c
 * 2、按照ThreadA->a、ThreadB->b、ThreadC->c顺序循环输出10次
 *
 * @author lipengfei
 * @create 2019-02-21 16:49
 **/
public class AliInterviewLockCondition {

    public static Lock lock = new ReentrantLock();
    public static volatile int shouldExecThread = 1;
    public static Condition conditionA;
    public static Condition conditionB;
    public static Condition conditionC;

    public static void main(String[] args) {
        conditionA = lock.newCondition();
        conditionB = lock.newCondition();
        conditionC = lock.newCondition();

        for (int i = 0; i < 10; i++) {
            new Thread(new ThreadAA()).start();
            new Thread(new ThreadBB()).start();
            new Thread(new ThreadCC()).start();
        }

    }


}

class ThreadAA implements Runnable {
    @Override
    public void run() {
        AliInterviewLockCondition.lock.lock();

        try {
            // 只有shouldExecThread=1时，A线程执行；否则A线程阻塞
            while (AliInterviewLockCondition.shouldExecThread != 1) {
                try {
                    AliInterviewLockCondition.conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("ThreadA->a");

            AliInterviewLockCondition.shouldExecThread = 2;
            AliInterviewLockCondition.conditionB.signal();
        } finally {
            AliInterviewLockCondition.lock.unlock();
        }
    }
}

class ThreadBB implements Runnable {
    @Override
    public void run() {
        AliInterviewLockCondition.lock.lock();

        try {
            while (AliInterviewLockCondition.shouldExecThread != 2) {
                try {
                    AliInterviewLockCondition.conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("ThreadB->b");

            AliInterviewLockCondition.shouldExecThread = 3;
            AliInterviewLockCondition.conditionC.signal();
        } finally {
            AliInterviewLockCondition.lock.unlock();
        }
    }
}

class ThreadCC implements Runnable {
    @Override
    public void run() {
        AliInterviewLockCondition.lock.lock();

        try {
            while (AliInterviewLockCondition.shouldExecThread != 3) {
                try {
                    AliInterviewLockCondition.conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("ThreadC->c");

            AliInterviewLockCondition.shouldExecThread = 1;
            AliInterviewLockCondition.conditionA.signal();
        } finally {
            AliInterviewLockCondition.lock.unlock();
        }
    }
}
