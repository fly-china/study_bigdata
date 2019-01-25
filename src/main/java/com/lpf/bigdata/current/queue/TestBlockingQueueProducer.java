package com.lpf.bigdata.current.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 测试阻塞队列的生产者
 *
 * @author lipengfei
 * @create 2018-09-28 17:17
 **/
public class TestBlockingQueueProducer implements Runnable{

    BlockingQueue<String> queue;
    Random random  = new Random();


    public TestBlockingQueueProducer(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {

        try{
            Thread.sleep(random.nextInt(10));

            String task = Thread.currentThread().getName()+ " made a job, " +    System.currentTimeMillis();

            System.out.println(task);

            queue.put(task); // put()为阻塞方法

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String login(String userName, String pwd){
        System.out.println("userName=" + userName + "---pwd=" + pwd);
        return "登录成功";
    }
}
