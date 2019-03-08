package com.lpf.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lipengfei
 * @create 2019-03-06 13:34
 **/
public class Demo1 {

    private static Map<Integer, Long> cacheMap = new HashMap<>();

    public Long climbStairs(int n) {
        if (n == 1)
            return 1l;
        if (n == 2)
            return 2l;

        Long num_1;
        if ((num_1 = cacheMap.get(Integer.valueOf(n - 1))) == null) {
            num_1 = climbStairs(n - 1);
            cacheMap.put(Integer.valueOf(n - 1), num_1);
        }

        Long num_2;
        if ((num_2 = cacheMap.get(Integer.valueOf(n - 2))) == null) {
            num_2 = climbStairs(n - 2);
            cacheMap.put(Integer.valueOf(n - 2), num_2);
        }


        return num_1 + num_2;
    }
}
