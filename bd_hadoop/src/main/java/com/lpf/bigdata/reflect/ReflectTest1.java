package com.lpf.bigdata.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author lipengfei
 * @create 2018-09-28 18:01
 **/
public class ReflectTest1 {

    public static void main(String[] args) {
        try {
            List list= new ArrayList();

            Class<?> clazz = Class.forName("com.lpf.interview.current.queue.TestBlockingQueueProducer");
            System.out.println(clazz);
            Constructor<?> constructor = clazz.getConstructor(BlockingQueue.class);

            BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>(2);
            Object object = constructor.newInstance(blockingQueue);

            Method method = clazz.getDeclaredMethod("run");
            method.invoke(object);

            System.out.println("---------------");
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                System.out.println("实现接口：" + anInterface);
            }
            System.out.println("---------------");

            Method method2 = clazz.getDeclaredMethod("login", String.class, String.class);
            method2.setAccessible(true);
            Object returnStr = method2.invoke(object, "lpf", "1018");
            System.out.println(returnStr.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
