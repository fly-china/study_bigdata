//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
//
// Related Topics 栈 数组 单调栈 👍 2832 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * [84]-柱状图中最大的矩形
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new LargestRectangleInHistogram().new Solution();
        System.out.println(solution.largestRectangleArea(new int[]{0}));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 单调栈的解法


        public int largestRectangleArea(int[] heights) {

            // 数组扩容，首尾都赋值 0。简化边界问题的处理
            int[] newHeights = new int[heights.length + 2];
            System.arraycopy(heights, 0, newHeights, 1, heights.length);

            Deque<Integer> stack = new LinkedList<>();
            int result = 0;
            stack.push(0);

            for (int i = 1; i < newHeights.length; i++) {
                while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                    Integer popIdx = stack.pop();
                    int midVal = newHeights[popIdx];
                    int leftIdx = -1;
                    if (!stack.isEmpty()) {
                        leftIdx = stack.peek();
                    }
                    int area = midVal * (i - leftIdx - 1);
                    result = Math.max(area, result);
                }
                stack.push(i);
            }

            return result;
        }

        public int largestRectangleArea2(int[] heights) {

            Deque<Integer> stack = new LinkedList<>();
            int result = Integer.MIN_VALUE;
            stack.push(0);

            for (int i = 1; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    Integer popIdx = stack.pop();
                    int midVal = heights[popIdx];
                    int leftIdx = -1;
                    if (!stack.isEmpty()) {
                        leftIdx = stack.peek();
                    }
                    int area = midVal * (i - leftIdx - 1);
                    result = Math.max(area, result);
                }
                stack.push(i);
            }

            while (!stack.isEmpty()) {
                Integer popIdx = stack.pop();
                int midVal = heights[popIdx];
                int rightIdx = heights.length;
                int leftIdx = -1;
                if (!stack.isEmpty()) {
                    leftIdx = stack.peek();
                }
                int area = midVal * (rightIdx - leftIdx - 1);
                result = Math.max(area, result);
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}