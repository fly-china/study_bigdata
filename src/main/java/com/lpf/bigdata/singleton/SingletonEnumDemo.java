package com.lpf.bigdata.singleton;

/**
 * 用enum实现Singleton时我曾介绍过三个特性，自由序列化，线程安全，保证单例'
 *
 * 由于enum是通过继承了Enum类实现的，enum结构不能够作为子类继承其他类，但是可以用来实现接口。
 * 此外，enum类也不能够被继承，在反编译中，我们会发现该类是final的
 */
public enum SingletonEnumDemo implements IPay{

    INSTANCE;

    public void otherMethods(){
        System.out.println("Something");
    }

    @Override
    public void pay() {
        System.out.println("pay success");
    }


    public static void main(String[] args) {
        SingletonEnumDemo s =  SingletonEnumDemo.INSTANCE;

        s.otherMethods();
        s.pay();
    }


}
