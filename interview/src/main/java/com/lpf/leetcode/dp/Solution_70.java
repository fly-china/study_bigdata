package com.lpf.leetcode.dp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 爬楼梯问题
 * 问题：共有n阶楼梯，每次仅可以爬 1 或 2 个台阶，有多少种不同的方法可以爬到楼顶呢？
 *
 * @author lipengfei
 * @create 2019-03-06 11:38
 **/
public class Solution_70 {

    private static AtomicInteger count = new AtomicInteger(0);
    private static Map<Integer, Long> cacheMap = new HashMap<>();

    @Test
    public void testMethod() {
        int stairsNum = 20;
        long startTime = System.currentTimeMillis();
        long nums = climbStairs(stairsNum);
        long endTime = System.currentTimeMillis();

        System.out.println(stairsNum + "阶楼梯共有" + nums + "种走法。");
        System.out.println("计算耗时：" + ((endTime - startTime)) + "毫秒");
        System.out.println("共计调用次数：" + count);

    }


    /**
     * 方法一：纯递归解法 f(n) = f(n-1) + f(n-2)
     *
     * @param n 楼梯阶数
     * @return
     */
    private int climbStairs(int n) {

        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 方法二：递归+缓存解法 f(n) = f(n-1) + f(n-2)
     *
     * @param n 楼梯阶数
     * @return
     */
    private Long climbStairs2(int n) {
        count.getAndIncrement();
//        System.out.println("统计次数：" + count);

        if (n == 1l)
            return 1l;
        if (n == 2l)
            return 2l;

        Long num_1;
        if ((num_1 = cacheMap.get(Integer.valueOf(n - 1))) == null) {
            num_1 = climbStairs2(n - 1);
            cacheMap.put(Integer.valueOf(n - 1), num_1);
        }

        Long num_2;
        if ((num_2 = cacheMap.get(Integer.valueOf(n - 2))) == null) {
            num_2 = climbStairs2(n - 2);
            cacheMap.put(Integer.valueOf(n - 2), num_2);
        }

        return num_1 + num_2;
    }


    /**
     * 方法三：非递归解法
     *
     * @param n 楼梯阶数
     * @return
     */
    private int climbStairs3(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int nums = 0;
        int lastF1 = 2;
        int lastF2 = 1;
        for (int i = 3; i <= n; i++) {
            nums = lastF1 + lastF2;

            lastF2 = lastF1;
            lastF1 = nums;
        }
        return nums;
    }


    /**
     * 动态规划解法
     *
     * @param n
     * @return
     */
    private int climbStairs4(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];

    }
}
