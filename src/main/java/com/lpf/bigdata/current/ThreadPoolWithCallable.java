package com.lpf.bigdata.current;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用带有callable接口的线程池
 *
 * @author lipengfei
 * @create 2018-09-28 16:08
 **/
public class ThreadPoolWithCallable {

    public static int times = 100;//100,1000,10000

    public static ArrayBlockingQueue arrayWorkQueue = new ArrayBlockingQueue(1000);
    public static ThreadPoolExecutor threadPool2 = new ThreadPoolExecutor(5, //corePoolSize线程池中核心线程数
            10,
            60,
            TimeUnit.SECONDS,
            arrayWorkQueue,
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );
    ExecutorService threadPool = Executors.newFixedThreadPool(4);


    public static void main(String[] args) throws ExecutionException, InterruptedException {


        List<Future> results = new ArrayList<>();

        for ( int i = 0; i < 5; i++) {

//            Future<String> submit = threadPool.submit(new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    System.out.println(Thread.currentThread().getName() + "-->正在进行工作");
//                    Thread.sleep(5000);
//                    System.out.println(Thread.currentThread().getName() + "-->工作完成");
//
//                    return "b-----" + Thread.currentThread().getName();
//                }
//            });

            int finalI = i;
            Future<String> submit = threadPool2.submit(() -> {

                        System.out.println(Thread.currentThread().getName() + "-->正在进行工作" + finalI);
                        Thread.sleep(3000);
                        if (finalI == 3) {
                            int ine = 1 / 0;
                        }
                        System.out.println(Thread.currentThread().getName() + "-->工作完成");

                        return "b-----" + Thread.currentThread().getName();
                    }

            );

            // 从Future中get结果，这个方法是会被阻塞的，一直等到线程任务返回结果。才会继续向下走
//            System.out.println("获取线程的处理结果为：" + submit.get());

            results.add(submit);
        }

        for (Future future : results) {
            boolean done = future.isDone();
            System.out.println(done ? "已完成" : "未完成");
            while (!done) {

                System.out.println("-------怎么还未完成");
                Thread.sleep(1000);
                done = future.isDone();
            }
            System.out.println("线程返回结果：" + future.get());
        }

        Integer a = 10;
        Integer b = 100;
        Future<?> result = threadPool2.submit(() -> sum(a, b));
        System.out.println(result.get());

        Long start = System.currentTimeMillis();
        threadPool2.shutdown();
        while (true) {
            if (threadPool2.isTerminated()) {
                Long end = System.currentTimeMillis();
                System.out.println("线程池关闭耗时：" +  (end - start));
                break;
            }
        }
    }


    public static Integer sum(Integer a, Integer b) {
        return a + b;
    }
}
