package com.lpf.jol.singleton;

/**
 * 双重检查的单例
 **/
public class DcSingleton {

    private static DcSingleton INSTANCE;

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
}
