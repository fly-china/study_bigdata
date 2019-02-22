package com.lpf.interview.current.timer;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 熟悉Timer类
 *
 * @author lipengfei
 * @create 2019-02-21 16:32
 **/
public class TimerBase1 {

    @Test
    public void demo1() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //实际中会扔一个对象进来，我们就可以在这里操作这个对象的所有方法了
                System.out.println("任务执行");
            }
        }, 10000, 1000);

        // 打印秒钟，一秒输出一次,用来方便观察的
        while (true) {
            System.out.println("当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
