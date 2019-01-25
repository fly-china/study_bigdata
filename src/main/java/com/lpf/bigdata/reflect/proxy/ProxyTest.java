package com.lpf.bigdata.reflect.proxy;

/**
 * @author lipengfei
 * @create 2018-09-28 18:53
 **/
public class ProxyTest {

    public static void main(String[] args) {


        System.out.println(ProxyBoss.getProxyBoss().getPhoneNumById("iphone X"));

        System.out.println("------------------------------------");

        IBoss proxy = ProxyBoss.getProxy(200, IBoss.class, BossImpl.class);
        System.out.println(proxy.getPhoneNumById("iphone Xs MAX"));

        System.out.println("------------------------------------");

        IBoss proxy2 = ProxyBoss.getProxy(100, IBoss.class, BossImpl.class);
        System.out.println(proxy.getClothesNumById("NIKE"));

    }
}
