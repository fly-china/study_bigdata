package com.lpf.bojun;

import java.util.*;

class Solution {
    public int findShortestSubArray(int[] nums) {
        // 统计每个数字的出现次数、首次出现位置和最后出现位置
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> firstOccurrence = new HashMap<>();
        Map<Integer, Integer> lastOccurrence = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 更新出现次数
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            // 记录首次出现位置
            if (!firstOccurrence.containsKey(num)) {
                firstOccurrence.put(num, i);
            }
            // 每次更新最后出现位置
            lastOccurrence.put(num, i);
        }

        // 获取数组的度（最大出现次数）
        int maxDegree = Collections.max(countMap.values());
        int minLength = Integer.MAX_VALUE;

        // 遍历所有出现次数等于最大度的数字
        for (int num : countMap.keySet()) {
            if (countMap.get(num) == maxDegree) {
                // 计算该数字对应的子数组长度
                int length = lastOccurrence.get(num) - firstOccurrence.get(num) + 1;
                minLength = Math.min(minLength, length);
            }
        }

        return minLength;
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // 测试用例1：示例1
        testCase1(solution);
        
        // 测试用例2：示例2
        testCase2(solution);
        
        // 测试用例3：单元素数组
        testCase3(solution);
    }

    private static void testCase1(Solution solution) {
        int[] nums = {1, 2, 2, 3, 1};
        int result = solution.findShortestSubArray(nums);
        System.out.println("测试用例1: " + result + " (预期: 2)");
    }

    private static void testCase2(Solution solution) {
        int[] nums = {1, 2, 2, 3, 1, 4, 2};
        int result = solution.findShortestSubArray(nums);
        System.out.println("测试用例2: " + result + " (预期: 6)");
    }

    private static void testCase3(Solution solution) {
        int[] nums = {5};
        int result = solution.findShortestSubArray(nums);
        System.out.println("测试用例3: " + result + " (预期: 1)");
    }
}