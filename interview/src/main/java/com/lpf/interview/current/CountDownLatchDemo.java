package com.lpf.interview.current;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch不但可以做“单个线程等待多线程的信号”，也可以做“多个线程等待某一个线程的信号”
 *
 * 正是因为可以“多线程等待某一线程”，即多线程调用await()方法，等待被唤醒，下面实例代码中的：startSignal.await()
 *
 * 因为CountDownLatch的await()多个线程可以调用多次，当调用多次的时候这些线程都要进入AQS队列中排队，
 * 当count次数减为0的时候，它们都需要被唤醒，继续执行任务，
 * 如果使用互斥锁则不行，互斥锁在多个线程之间是互斥的，一次只能唤醒一个，不能保证当count减为0的时候这些调用了await()方法等待的线程都被唤醒。
 * 所以。CountDownLatch使用共享锁，当count=0时，“一个接着一个地唤醒”全部共享锁的等待者
 *
 * @author lipengfei
 * @date 2019-11-14 16:32
 **/
public class CountDownLatchDemo {

    /**
     * 第一段，5个辅助线程等待开始的信号，信号由主线程发出，所以5个辅助线程调用startSignal.await()方法等待开始信号，
     * 当主线程的事儿干完了，调用startSignal.countDown()通知辅助线程开始干活。
     * <p>
     * 第二段，主线程等待5个辅助线程完成的信号，信号由5个辅助线程发出，所以主线程调用doneSignal.await()方法等待完成信号，
     * 5个辅助线程干完自己的活儿的时候调用doneSignal.countDown()方法发出自己的完成的信号，当完成信号达到5个的时候，唤醒主线程继续执行后续的逻辑。
     */
    public static void main(String[] args) throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println("doneSignal线程都在等待startSignal下令开工");
                    startSignal.await();
                    System.out.println(Thread.currentThread().getName() + "线程，工作了");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    doneSignal.countDown();
                }
            }, "donwSignal-" + i).start();
        }

        Thread.sleep(2000);
        System.out.println("startSignal线程，干完自己活了，下令所有doneSignal可以开工了*******");
        startSignal.countDown();

        Thread.sleep(2000);
        System.out.println("startSignal线程在等待所有doneSignal都干完活");
        doneSignal.await();

        System.out.println("主线程等上述任务都完成后，还可以干下面的事情...............");
    }
}
