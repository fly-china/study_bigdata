package com.lpf.testbak;

/**
 * @author lipengfei
 * @create 2025-03-04 11:41
 **/
public class SingletonPattern {
    private static final Object lock = new Object();
    private static volatile SingletonPattern singleton;

    private SingletonPattern() {
    }

    public static SingletonPattern getObj() {
        if (singleton == null) {
            synchronized (SingletonPattern.class) {
                if (singleton == null) {
                    singleton = new SingletonPattern();
                }
            }
        }
        return singleton;
    }
}
