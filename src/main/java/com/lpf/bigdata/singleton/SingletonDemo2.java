package com.lpf.bigdata.singleton;

/**
 * 把Singleton实例放到一个静态内部类中，这样就避免了静态实例在Singleton类加载的时候就创建对象，
 * 并且由于静态内部类只会被加载一次，所以这种写法也是线程安全的
 */
public class SingletonDemo2 {

    private SingletonDemo2() {
    }


    private static class SingletonHandler {
        private final static SingletonDemo2 instance = new SingletonDemo2();
    }

    public static SingletonDemo2 getInstance() {
        return SingletonHandler.instance;
    }

}
