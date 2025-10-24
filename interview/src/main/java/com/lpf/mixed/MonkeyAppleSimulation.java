package com.lpf.mixed;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonkeyAppleSimulation {
    private volatile int apples = 20;
    private final Lock lock = new ReentrantLock();
    private final Condition conditionA = lock.newCondition();
    private final Condition conditionB = lock.newCondition();
    private boolean monkeyATurn = true; // 控制轮流的标志

    // 猴子A，每次拿2个苹果
    public void monkeyA() {
        while (true) {
            lock.lock();
            try {
                while (apples < 2 || !monkeyATurn) {
                    conditionA.await(); // 如果不是猴子A的回合或苹果不够，等待
                }
                System.out.println("Monkey A took 2 apples, remaining: " + (apples - 2));
                apples -= 2;
                monkeyATurn = false; // 切换到猴子B的回合
                conditionB.signal(); // 通知猴子B可以拿苹果了
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    // 猴子B，每次拿3个苹果
    public void monkeyB() {
        while (true) {
            lock.lock();
            try {
                while (apples < 3 || monkeyATurn) {
                    conditionB.await(); // 如果不是猴子B的回合或苹果不够，等待
                }
                System.out.println("Monkey B took 3 apples, remaining: " + (apples - 3));
                apples -= 3;
                monkeyATurn = true; // 切换回猴子A的回合
                conditionA.signal(); // 通知猴子A可以拿苹果了
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        MonkeyAppleSimulation simulation = new MonkeyAppleSimulation();

        Thread monkeyAThread = new Thread(simulation::monkeyA);
        Thread monkeyBThread = new Thread(simulation::monkeyB);

        monkeyBThread.start();
        monkeyAThread.start();
    }
}