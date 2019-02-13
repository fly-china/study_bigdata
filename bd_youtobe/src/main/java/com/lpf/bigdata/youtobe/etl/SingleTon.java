package com.lpf.bigdata.youtobe.etl;

/**
 * @author lipengfei
 * @create 2019-01-31 11:11
 **/
public class SingleTon {

    private SingleTon() {
    }


    private static class SingleTonHandler {
        private static SingleTon singleTon = new SingleTon();
    }

    public SingleTon getInstance() {

        return SingleTonHandler.singleTon;
    }
}
