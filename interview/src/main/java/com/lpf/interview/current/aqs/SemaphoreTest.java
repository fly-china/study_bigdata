package com.lpf.interview.current.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流，即在流量突然增大的时候，上层要能够限制住突然的大流量对下游服务的冲击，
 * 在分布式系统中限流一般做在网关层，当然在个别功能中也可以自己简单地来限流，
 * <p>
 * 比如秒杀场景，假如只有10个商品需要秒杀，那么，服务本身可以限制同时只进来100个请求，其它请求全部作废，这样服务的压力也不会太大。
 * <p>
 * 使用Semaphore就可以直接针对这个功能来限流，以下是代码实现
 */
public class SemaphoreTest {
    // 初始化许可证100,即限流100
    private static final Semaphore SEMAPHORE = new Semaphore(100);
    private static final AtomicInteger failCount = new AtomicInteger(0);
    private static final AtomicInteger successCount = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

//        System.out.println("销毁所有许可证：" + SEMAPHORE.drainPermits());// 许可证销毁，所有线程获取不到许可
        System.out.println("SEMAPHORE.isFair()=" + SEMAPHORE.isFair());
        System.out.println("SEMAPHORE.availablePermits()=" + SEMAPHORE.availablePermits());
        System.out.println("SEMAPHORE.hasQueuedThreads()=" + SEMAPHORE.hasQueuedThreads());

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> seckill()).start();
        }

        Thread.sleep(5_000);
        System.out.println("失败次数统计：" + failCount.get());
        System.out.println("成功次数统计：" + successCount.get());

    }

    private static boolean seckill() {
        if (!SEMAPHORE.tryAcquire()) {
            System.out.println("no permits, count=" + failCount.incrementAndGet());
            return false;
        }

        try {
            // 处理业务逻辑
            Thread.sleep(500);
            System.out.println("seckill success, count=" + successCount.incrementAndGet()
                    + ",availablePermits=" + SEMAPHORE.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            SEMAPHORE.release();
        }
        return true;
    }
}