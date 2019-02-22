package com.lpf.interview.current;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lipengfei
 * @create 2019-02-21 17:16
 **/
public class CyclicBarrierDemo1 {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        int numberWaiting = cyclicBarrier.getNumberWaiting();
        System.out.println("当前等待线程数量：" + numberWaiting);



        Thread ta = new Thread(new ThreadAction("张三", cyclicBarrier));
        Thread tb = new Thread(new ThreadAction("李四", cyclicBarrier));
        Thread tc = new Thread(new ThreadAction("王五", cyclicBarrier));

        ta.start();
        tb.start();
        tc.start();
    }

}

class ThreadAction implements Runnable {
    private String userName;
    private CyclicBarrier cyclicBarrier;

    public ThreadAction(String userName, CyclicBarrier cyclicBarrier) {
        this.userName = userName;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // 事件1、上大巴
            Thread.sleep((long)(Math.random()*5000));
            System.out.println(Thread.currentThread().getName() + "--userName:" + userName + "--已上大巴，等待出发...");
            int waitTheadNum = cyclicBarrier.getNumberWaiting();
            if(waitTheadNum == 2){
                System.out.println("所有人已上大巴，出发了去P城");
            }
            cyclicBarrier.await();// 使用过后的cyclicBarrier可再次重复使用

            Thread.sleep((long)(Math.random()*5000));
            System.out.println(Thread.currentThread().getName() + "--userName:" + userName + "--P城游玩完毕，前往集合点...");
            waitTheadNum = cyclicBarrier.getNumberWaiting();
            if(waitTheadNum == 2){
                System.out.println("P城集合完毕，一同前往军事基地");
            }
            cyclicBarrier.await();// 使用过后的cyclicBarrier可再次重复使用

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


    }
}
