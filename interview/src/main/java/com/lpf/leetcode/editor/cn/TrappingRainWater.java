//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
//
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 5398 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

/**
 * [42]-接雨水
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
        int[] nums = {5, 3, 2, 1, 4};
        System.out.println(solution.trap(nums));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 双指针的解法
         * 核心：当前列雨水面积：min(左边柱子的最高高度，记录右边柱子的最高高度) - 当前柱子高度。
         */
        public int trap(int[] nums) {
            if (nums == null || nums.length < 3) {
                return 0;
            }

            int[] leftMaxNums = new int[nums.length];
            int[] rightMaxNums = new int[nums.length];

            // 1、找到每个列左侧最高的柱子。忽略掉 i=0 的柱子，以为无法接水
            leftMaxNums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                leftMaxNums[i] = Math.max(nums[i], leftMaxNums[i - 1]);
            }

            // 1、找到每个列右侧最高的柱子。忽略掉最右侧的柱子，以为无法接水
            rightMaxNums[nums.length - 1] = nums[nums.length - 1];
            for (int j = nums.length - 2; j >= 0; j--) {
                rightMaxNums[j] = Math.max(nums[j], rightMaxNums[j + 1]);
            }

            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                int area = Math.min(leftMaxNums[i], rightMaxNums[i]) - nums[i];
                if (area > 0) {
                    res += area;
                }
            }

            return res;
        }

        /**
         * 单调栈的解法
         */
        public int trap2(int[] nums) {
            if (nums == null || nums.length < 3) {
                return 0;
            }

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            int totalArea = 0;

            for (int i = 1; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) { // 单调减的栈
                    Integer popIdx = stack.pop(); // 弹出的是容器的底儿
                    if (!stack.isEmpty()) { // 确保还有做边界
                        Integer leftIdx = stack.peek(); // peek 的是它的左边界

                        int height = Math.min(nums[i], nums[leftIdx]) - nums[popIdx];  // i 是容器的右边界
                        int weight = i - leftIdx - 1;
                        totalArea += height * weight;
                    }

                }
                stack.push(i);
            }

            return totalArea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}