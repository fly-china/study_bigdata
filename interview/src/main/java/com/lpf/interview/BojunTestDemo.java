package com.lpf.interview;

/**
 * @author lipengfei
 * @create 2024-10-31 19:47
 **/
public class BojunTestDemo {

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 如果当前子数组和为负数，则丢弃当前子数组，从当前元素重新开始
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // 更新最大子数组和
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
