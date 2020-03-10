package com.lpf.jol;

/**
 * 测试缓存行cache line对齐，未填充对齐前的效果
 * 稳定后的平均耗时：240ms左右
 *
 * @author lipengfei
 * @create 2020-03-08 22:17
 **/
public class CacheLinePaddingBefore {

    private static class T {
        public volatile long x = 1L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
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
        System.out.println("耗时：" + (end - start)/100_0000);

    }
}
