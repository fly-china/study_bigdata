package com.lpf.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author lipengfei
 * @create 2020-03-09 17:31
 **/
public class TestSynchronized {

    static Object lock = new Object();

    public static void main(String[] args) {

        System.out.println("加锁前**********************");
        String layout0 = ClassLayout.parseInstance(lock).toPrintable();
        System.out.println(layout0);

        System.out.println("***********加锁时***********");
        synchronized (lock) {
            String layout1 = ClassLayout.parseInstance(lock).toPrintable();
            System.out.println(layout1);
        }

        System.out.println("*********************释放锁后*");
        String layout2 = ClassLayout.parseInstance(lock).toPrintable();
        System.out.println(layout2);
    }
}
