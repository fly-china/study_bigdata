package com.lpf.jol;

/**
 * 测试缓存行cache line对齐，填充对齐后的效果
 * 稳定后平均耗时：140ms
 * <p>
 * 著名的disruptor框架中，有跟经典的代码：
 * public long p1, p2, p3, p4, p5, p6, p7;
 * private volatile long cursor = INITIAL_CURSOR_VALUE;
 * public long p8, p9, p10, p11, p12, p13, p14;
 * <p>
 * 即L1、L2、L3高级缓存中，每次被内存获取的块的大小为64字节，即缓存块
 * 若缓存块中即包含x，又包含y，那么x、y的更改都要去通知其他cpu它们的变更。
 * long占8字节，为了凑够64字节，我们在x的前后各填充56字节，使其不管以什么切割方式拿到的缓存块中，
 * 只有x有用，就避免了内存块中其他变量变更带来的通知cpu的损耗。
 *
 * @author lipengfei
 * @create 2020-03-08 22:17
 **/
public class CacheLinePaddingAfter {

    // 定义7个long类型变量，进行缓存行填充
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    private static class Entity {
        public volatile long p1, p2, p3, p4, p5, p6,p7;
        // 使用@sun.misc.Contended注解，必须添加此参数：-XX:-RestrictContended
//         @Contended
        public volatile long x = 0L;
    }

    public static Entity[] arr = new Entity[2];

    static {
        arr[0] = new Entity();
        arr[1] = new Entity();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 1000_0000; i++) {
                arr[0].x = i;
            }
        }, "ThreadA");

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 1000_0000; i++) {
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
