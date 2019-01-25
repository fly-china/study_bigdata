package com.lpf.bigdata.current;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lipengfei
 * @create 2018-09-28 15:23
 **/
public class MyReentranLock {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {
        final MyReentranLock test = new MyReentranLock();

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu数量为：" + processors);
        new Thread(){
                public void run(){
                    test.read(Thread.currentThread());
                    test.write(Thread.currentThread());
                }
        }.start();

        new Thread(){
            public void run(){
                test.read(Thread.currentThread());
                test.write(Thread.currentThread());
            }
        }.start();

        Thread.sleep(10000);
    }





    public void read(Thread thread){
        rwLock.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1000){
                System.out.println(thread.getName() + "---正在进行读操作...");
                Thread.sleep(200);
            }

            System.out.println(thread.getName() + "---读操作完成");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }


    public void write(Thread thread){
        rwLock.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1000){
                System.out.println(thread.getName() + "---正在进行写操作...");
                Thread.sleep(100);
            }

            System.out.println(thread.getName() + "---写 操作完成");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }

}
