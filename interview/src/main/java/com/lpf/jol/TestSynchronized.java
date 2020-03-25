package com.lpf.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author lipengfei
 * @create 2020-03-09 17:31
 **/
public class TestSynchronized {

//    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        TestSynchronized lock = new TestSynchronized();
//        Object lock = new Object();
//        lock.toString();
        System.out.println(Integer.toBinaryString(lock.hashCode()));


        System.out.println("加锁前**********************");
        String layout0 = ClassLayout.parseInstance(lock).toPrintable();
        System.out.println(layout0);

        System.out.println("***********加锁时***********");
        synchronized (lock) {
            // -XX:BiasedLockingStartupDelay=0 偏向锁延时
            String layout1 = ClassLayout.parseInstance(lock).toPrintable();
            System.out.println(layout1);
        }

        System.out.println("*********************释放锁后*");
        String layout2 = ClassLayout.parseInstance(lock).toPrintable();
        System.out.println(layout2);
    }

}
