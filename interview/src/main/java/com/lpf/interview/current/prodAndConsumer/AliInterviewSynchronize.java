package com.lpf.interview.current.prodAndConsumer;

/**
 * 三个线程顺序循环输出10次(synchronized实现)
 * 要求：
 * 1、三个线程：ThreadA、ThreadB、ThreadC分别输出a,b,c
 * 2、按照ThreadA->a、ThreadB->b、ThreadC->c顺序循环输出10次
 *
 * @author lipengfei
 * @create 2019-02-13 13:58
 **/
public class AliInterviewSynchronize {

    public static volatile Boolean isA = true;
    public static volatile Boolean isB = false;
    public static volatile Boolean isC = false;


    public static void main(String[] args) throws InterruptedException {
        int nums = 10;

        for (int i = 0; i < nums; i++) {
            Thread t_a = new Thread(new ThreadA((i + 1)));
            Thread t_b = new Thread(new ThreadB((i + 1)));
            Thread t_c = new Thread(new ThreadC((i + 1)));

            t_a.start();
            t_b.start();
            t_c.start();

        }


    }

}

class ThreadA implements Runnable {

    private Integer outNum;

    public ThreadA(Integer outNum) {
        this.outNum = outNum;

    }

    @Override
    public void run() {

        synchronized (AliInterviewSynchronize.class) {
//            System.out.println("进入线程C,isC=" + AliInterview.isC);

            while (!AliInterviewSynchronize.isA) {// 不满足生产条件
                try {
                    AliInterviewSynchronize.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 满足生产条件
            AliInterviewSynchronize.isA = false;
            AliInterviewSynchronize.isB = true;
            System.out.println("第" + outNum + "次ThreadA->a");

            // 唤醒其他线程
            AliInterviewSynchronize.class.notifyAll();
        }

    }
}

class ThreadB implements Runnable {


    private Integer outNum;

    public ThreadB(Integer outNum) {
        this.outNum = outNum;

    }

    @Override
    public void run() {

        synchronized (AliInterviewSynchronize.class) {
//            System.out.println("进入线程B,isB=" + AliInterview.isB);
            while (!AliInterviewSynchronize.isB) {// 不满足生产条件
                try {
                    AliInterviewSynchronize.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 满足生产条件
            AliInterviewSynchronize.isB = false;
            AliInterviewSynchronize.isC = true;
            System.out.println("第" + outNum + "次ThreadB->b");

            // 唤醒其他线程
            AliInterviewSynchronize.class.notifyAll();
        }

    }
}

class ThreadC implements Runnable {


    private Integer outNum;

    public ThreadC(Integer outNum) {
        this.outNum = outNum;

    }

    @Override
    public void run() {

        synchronized (AliInterviewSynchronize.class) {
//            System.out.println("进入线程C,isC=" + AliInterview.isC);
            while (!AliInterviewSynchronize.isC) {// 不满足生产条件
                try {
                    AliInterviewSynchronize.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 满足生产条件
            AliInterviewSynchronize.isC = false;
            AliInterviewSynchronize.isA = true;
            System.out.println("第" + outNum + "次ThreadC->c");

            // 唤醒其他线程
            AliInterviewSynchronize.class.notifyAll();
        }

    }
}

