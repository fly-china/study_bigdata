package com.lpf.interview.current.reentrantLock;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 死锁样例
 *
 * @author lipengfei
 * @date 2019-11-12 19:53
 **/
public class DeadLockDemo {


    public static void main(String[] args) throws Exception{
        testReentrantLock();
    }

    /**
     * 下面的测试用例证明：
     * 当一个线程持有写锁时，
     *  - 该线程可以继续获取读锁（还可以重入获取）；
     *  - 其他线程无法在获取读锁（写锁更甭想了），除非上一个获取写锁的线程释放掉写锁。
     *
     * @throws Exception
     */
    public static void testReentrantLock() throws Exception{
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
       final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        writeLock.lock();
        System.out.println("写锁已加");
        readLock.lock();
        System.out.println("读锁已加");
        readLock.lock();
        System.out.println("读锁再加一次");

        new Thread(() -> {
            readLock.lock();
            System.out.println("异步线程，读锁已加");
            readLock.unlock();
            System.out.println("异步线程，读锁释放");
        }).start();

        Thread.sleep(1000);
        readLock.unlock();
        System.out.println("读锁先释放一次");
        readLock.unlock();
        System.out.println("读锁再释放一次");
        Thread.sleep(2000);
        writeLock.unlock();
        System.out.println("写锁已释放");




    }

    public static void testDeadLock(String[] args) {
        Object a = new Object();
        Object b = new Object();

        new Thread(() ->{
            synchronized (a){
                System.out.println("线程1，获取A锁");
                // 阻塞线程1秒，不会释放当前线程占有的锁资源
                LockSupport.parkNanos(1_000_000_000);

                synchronized (b){
                    System.out.println("线程1，获取B锁");
                }
            }
        }).start();

        new Thread(() ->{
            synchronized (b){
                System.out.println("线程2，获取B锁");

                synchronized (a){
                    System.out.println("线程2，获取A锁");
                }
            }
        }).start();
    }
}
