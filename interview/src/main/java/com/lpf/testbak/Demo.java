package com.lpf.testbak;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lipengfei
 * @create 2025-02-26 15:05
 **/
public class Demo {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
        int maxCapacity = 5;
        Object lock = new Object();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> new Producer(queue, lock, maxCapacity).produce());
            executorService.submit(() -> new Consumer(queue, lock, maxCapacity).consume());
        }

        Thread.sleep(10_000L);
    }

}

class Producer {
    private BlockingQueue<String> queue;
    private int capacity;
    private Object lockObj;

    public Producer(BlockingQueue queue, Object lockObj, int capacity) {
        this.queue = queue;
        this.lockObj = lockObj;
        this.capacity = capacity;
    }

    public void produce() {
        synchronized (lockObj) {
            while (queue.size() == capacity) { // 队列满了
                System.out.println("队列满了。。。");
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.add("A" + System.currentTimeMillis());
            lockObj.notifyAll();
        }
    }
}

class Consumer {
    private BlockingQueue<String> queue;
    private int capacity;
    private Object lockObj;

    public Consumer(BlockingQueue queue, Object lockObj, int capacity) {
        this.queue = queue;
        this.lockObj = lockObj;
        this.capacity = capacity;
    }

    public void consume(){
        synchronized (lockObj) {
            while (queue.isEmpty()) {
                System.out.println("队列空了");
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            while (!queue.isEmpty()) {
                System.out.println("Consume, content=" + queue.poll());
            }
            lockObj.notifyAll();
        }
    }
}
