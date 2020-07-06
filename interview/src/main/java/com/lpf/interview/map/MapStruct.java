package com.lpf.interview.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lipengfei
 * @create 2019-02-12 13:57
 **/
public class MapStruct {


    public static void main(String[] args) {

        HashMap dataMap = new HashMap();
        dataMap.put("name", "lpf");

        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("name", "lpf");
        map.get("name");
    }



}
