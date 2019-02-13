package com.lpf.interview.current.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 测试阻塞队列的生产者
 *
 * @author lipengfei
 * @create 2018-09-28 17:17
 **/
public class TestBlockingQueueConsumer implements Runnable{

    BlockingQueue<String> queue;
    Random random  = new Random();


    public TestBlockingQueueConsumer(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {

        try{
            Thread.sleep(random.nextInt(10));
            System.out.println(Thread.currentThread().getName() + " trying obation.....");

            String task = queue.take();// 如果队列为空，会阻塞当前进程

            System.out.println(Thread.currentThread().getName() + " get a job.--" + task);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
