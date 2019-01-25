package com.lpf.bigdata.current.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lipengfei
 * @create 2018-09-28 16:27
 **/
public class MyBlcokingQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new LinkedBlockingQueue<>(2);

        TestBlockingQueueConsumer consumer = new TestBlockingQueueConsumer(queue);
        TestBlockingQueueProducer producer = new TestBlockingQueueProducer(queue);


        for (int i = 0; i < 5; i++) {
            new Thread(consumer, "Consumer" + (i + 1)).start();
        }

        Thread.sleep(3000);

        for (int i = 0; i < 3; i++) {
            new Thread(producer, "****Producer" + (i + 1)).start();
        }


        for (int i = 0; i < 3; i++) {
            new Thread(producer, "****Producer" + (i + 1)).start();
        }
    }


}
