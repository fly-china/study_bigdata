package com.lpf.jol;

/**
 * 说明：测试缓存行填充，此demo为：不进行缓存行填充
 * 思路：
 *      1、定义一个长度为2的数组arr，数组中是一个仅有一个long类型变量的对象；
 *      2、定义两个线程A和B，线程A修改arr[0]，线程B修改arr[1]。线程A和线程B并发修改1千万次；
 *      3、此处定义数组的目的是：保证线程A和线程B修改的变量尽可能是连续的，即两个变量在同一缓存行中，以模拟伪共享问题。
 *
 * 测试结果：稳定后的平均耗时：240ms左右
 *
 **/
public class CacheLinePaddingBefore {

    private static class Entity {
        public volatile long x = 1L;
    }

    public static Entity[] arr = new Entity[2];

    static {
        arr[0] = new Entity();
        arr[1] = new Entity();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            for (long i = 0; i < 1000_0000; i++) {
                arr[0].x = i;
            }
        }, "ThreadA");

        Thread threadB = new Thread(() -> {
            for (long i = 0; i < 1000_0000; i++) {
                arr[1].x = i;
            }
        }, "ThreadB");

        final long start = System.nanoTime();
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        final long end = System.nanoTime();
        System.out.println("耗时：" + (end - start) / 100_0000);

    }
}
