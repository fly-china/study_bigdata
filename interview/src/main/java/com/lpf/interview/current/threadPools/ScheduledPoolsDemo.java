package com.lpf.interview.current.threadPools;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 调度线程池
 *
 * @author lipengfei
 * @create 2019-05-24 16:05
 **/
public class ScheduledPoolsDemo {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    ScheduledExecutorService pools1 = Executors.newSingleThreadScheduledExecutor();
    ScheduledExecutorService pools2 = Executors.newScheduledThreadPool(5);
    ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);


    @Test
    public void testBaseMethod() throws Exception {


        System.out.println(LocalDateTime.now().format(formatter) + "方法开始执行，" + Thread.currentThread().getName());

//        // 定时调度
//        pools1.schedule(() -> {
//            System.out.println(LocalDateTime.now().format(formatter) + "任务执行...");
//        }, 3, TimeUnit.SECONDS);


        /**
         * 3 秒后开始执行，每隔两秒执行一次;
         * 如果任务执行时间过长，则间隔以执行时间为准。
         * 即频率=Max(初始化period， 任务执行时间)
         */
        ScheduledFuture<?> scheduledFuture = pools2.scheduleAtFixedRate(() -> {
            System.out.println(LocalDateTime.now().format(formatter) + "任务执行..." + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 1, TimeUnit.SECONDS);


        Thread.sleep(20000);
    }

}
