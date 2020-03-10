package com.lpf.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author lipengfei
 * @create 2020-03-09 17:31
 **/
public class TestSynchronized {
    public static void main(String[] args) {
        Object o = new Object();
        synchronized (o) {
            String layout = ClassLayout.parseInstance(o).toPrintable();
            System.out.println(layout);
        }

        String layout = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(layout);
    }
}
