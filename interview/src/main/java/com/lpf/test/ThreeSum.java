package com.lpf.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lipengfei
 * @create 2025-03-17 19:27
 **/
public class ThreeSum {

    /**
     * 给你一个包含n个整数的数组nums，判断nums中是否存在三个元素a，b，c，使得a + b + c = 0？请找出所有满足条件且不重复的三元组。
     * 示例：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = getRes(nums);
        System.out.println(JSONObject.toJSON(res));
    }

    private static List<List<Integer>> getRes(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums); // [-4,-1,-1,0,1,2]

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int curr = nums[i]; // -4
            int targetNum = -curr; // 4

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (nums[left] + nums[right] > targetNum) {
                    right--;
                } else if (nums[left] + nums[right] < targetNum) {
                    left++;
                } else {
                    res.add(Lists.newArrayList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }


        return res;
    }


}
