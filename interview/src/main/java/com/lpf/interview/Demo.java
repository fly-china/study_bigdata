package com.lpf.interview;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
 * 返回 A 的任意排列，使其相对于 B 的优势最大化。
 * 示例 1：
 * 输入：A = [2,7,11,15], B = [1,10,4,11]   []
 * 输出：[2,11,7,15]
 * <p>
 * 示例 2：
 * 输入：A = [12,24,8,32] , B = [13,25,32,11]
 * 输出：[24,32,8,12]
 * <p>
 * 提示： 1 <= A.length = B.length <= 10000 0 <= A <= 10^9 0 <= B <= 10^9
 *
 * @author lipengfei
 * @create 2025-02-21 16:07
 **/
public class Demo {
    public static void main(String[] args) {
//        int[] arrA = {2, 7, 11, 15};
//        int[] arrB = {1, 10, 4, 11};
        int[] arrA = {12,24,8,32};
        int[] arrB = {13,25,32,11};

        //  1、将数组 B 按照从大到小排序，并级联将对应的 idx 一起排序
        int[][] wrapArrB = new int[arrB.length][2];
        for (int i = 0; i < arrB.length; i++) {
            wrapArrB[i][0] = arrB[i];
            wrapArrB[i][1] = i;
        }

        // 2、将 wrapArrB 二维数据按照 wrapArrB[i][0] 从大到小排序
        Arrays.sort(wrapArrB, (o1, o2) -> Integer.compare(o2[0], o1[0]));
        Arrays.sort(arrA);

        // 3、贪心策略，left 指向 A 的最小值，right 指向 B 的最大值。
        // 优先使用 Max(A) 去满足 Max(B), 如果 Max(A) <= Max(B)，说明已经没有数能满足，那么就用田忌赛马的策略用 Min(A) 去 抵消掉 Max(B)
        int left = 0;
        int right = arrA.length - 1;
        int[] temp = new int[arrA.length];

        for (int i = 0; i < wrapArrB.length; i++) {
            if (arrA[right] > wrapArrB[i][0]) {
                temp[i] = arrA[right--];
            } else {
                temp[i] = arrA[left++];
            }
        }

        int[] res = new int[arrA.length];
        for (int i = 0; i < wrapArrB.length; i++) {
            res[wrapArrB[i][1]] = temp[i];
        }

//        System.out.println(JSONObject.toJSON(res));
    }
}
