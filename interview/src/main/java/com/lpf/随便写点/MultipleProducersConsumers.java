package com.lpf.随便写点;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int product = produce();
                queue.put(product);
                System.out.println("Produced: " + product + ", Queue size: " + queue.size());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int produce() {
        // 生产一个产品
        return (int) (Math.random() * 100);
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int product = queue.take();
                consume(product);
                System.out.println("Consumed: " + product + ", Queue size: " + queue.size());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void consume(int product) {
        // 消费一个产品
        System.out.println("消费到一个产品：" + product);
    }
}

public class MultipleProducersConsumers {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); // 队列容量为10

        // 创建并启动生产者线程
        for (int i = 0; i < 3; i++) {
            Thread producerThread = new Thread(new Producer(queue));
            producerThread.start();
        }

        // 创建并启动消费者线程
        for (int i = 0; i < 3; i++) {
            Thread consumerThread = new Thread(new Consumer(queue));
            consumerThread.start();
        }
    }
}