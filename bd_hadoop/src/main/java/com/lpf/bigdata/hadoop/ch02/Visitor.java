package com.lpf.bigdata.hadoop.ch02;

public class Visitor extends Thread {


    private boolean stop;

    @Override
    public void run() {
        int i = 0;
        while (!getStop()) {
            i++;
            System.out.println(".");
//            System.out.println(i);
        }
        System.out.println("完成。。。。" + i);
    }

    public void stopIt() {
        stop = true;
    }

    public boolean getStop() {
        return stop;
    }

    public static void main(String[] args) throws Exception {
        Visitor visitor = new Visitor();
        visitor.start();
        Thread.sleep(1000);
        visitor.stopIt();
        Thread.sleep(2000);
        System.out.println("主函数完成。。。。");
        System.out.println(visitor.getStop());
    }


}