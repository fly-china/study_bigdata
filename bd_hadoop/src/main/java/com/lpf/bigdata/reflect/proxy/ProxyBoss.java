package com.lpf.bigdata.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lipengfei
 * @create 2018-09-28 18:53
 **/
public class ProxyBoss {

    public static IBoss getProxyBoss() {


        Object proxyInstance = Proxy.newProxyInstance(BossImpl.class.getClassLoader(), new Class[]{IBoss.class}
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 代理进行加强处理

                        Integer value = (Integer) method.invoke(new BossImpl(), args);

                        value -= 500;
                        System.out.println("动态代理" + args[0] + "，降价500元");
                        return value;
                    }
                });


        return (IBoss) proxyInstance;
    }

    public static <T> T getProxy(final int jiangjia, final Class<?> intterfaceClass, final Class<?> implementClass) {

        return (T) Proxy.newProxyInstance(implementClass.getClassLoader(), new Class[]{intterfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        if (method.getName().equals("getPhoneNumById")) {
                            // 只代理 getPhoneNumById 方法

                            Integer value = (Integer) method.invoke(implementClass.newInstance(), args);
                            System.out.println("新动态代理getPhoneNumById方法，" + args[0] + "，降价" + jiangjia + "元");

                            return value - jiangjia;

                        }else if(method.getName().equals("getClothesNumById")){
                            // 只代理 getPhoneNumById 方法

                            Integer value = (Integer) method.invoke(implementClass.newInstance(), args);
                            System.out.println("新动态代理getClothesNumById方法，" + args[0] + "，不降价");

                            return value ;
                        }else {

                            return method.invoke(implementClass.newInstance(), args);
                        }
                    }
                });

    }
}
