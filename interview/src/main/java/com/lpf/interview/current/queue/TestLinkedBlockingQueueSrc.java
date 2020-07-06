package com.lpf.interview.current.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue源码测试类
 *
 * @author lipengfei
 * @date 2019-11-13 14:00
 **/
public class TestLinkedBlockingQueueSrc {

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

    public static void main(String[] args) throws Exception{
        testPutQueue();
    }

    /**
     * 测试put方法
     */
    public static void testPutQueue() throws InterruptedException {
        queue.put("a");
        queue.put("b");
        new Thread(()->{
            try {
                Thread.sleep(500);
                System.out.println("异步" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.put("c");

        System.out.println(queue.take());
        System.out.println(queue.take());
    }


}
