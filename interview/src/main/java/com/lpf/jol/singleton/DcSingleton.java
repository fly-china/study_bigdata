package com.lpf.jol.singleton;

/**
 * 双重检查的单例
 *
 * @author lipengfei
 * @create 2020-07-01 22:40
 **/
public class DcSingleton {


    private DcSingleton() {
        try {
            Thread.sleep(1500);
            System.out.println("休息了1秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static volatile DcSingleton INSTANCE;

    public static DcSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DcSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DcSingleton();
                }
            }

        }
        return INSTANCE;
    }

    public void getValue() {
        System.out.println("------************------");
    }

}
