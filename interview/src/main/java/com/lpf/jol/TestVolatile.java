package com.lpf.jol;

/**
 * 测试volatile关键字
 *
 * @author lipengfei
 * @create 2020-04-08 16:03
 **/
public class TestVolatile {

    public static volatile int counter = 1;

    public static void main(String[] args){
        counter = 2;
        System.out.println(counter);
    }
}
