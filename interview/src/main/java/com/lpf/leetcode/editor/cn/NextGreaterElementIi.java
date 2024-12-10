//给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素
// 。 
//
// 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 
//。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数； 
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,2,3,4,3]
//输出: [2,3,4,-1,4]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// Related Topics 栈 数组 单调栈 👍 1013 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * [503]-下一个更大元素 II
 */
public class NextGreaterElementIi {
    public static void main(String[] args) {
        Solution solution = new NextGreaterElementIi().new Solution();
        int[] nums = {-1, 0};
        System.out.println(JSONObject.toJSON(solution.nextGreaterElements(nums)));

        System.out.println(Integer.MAX_VALUE);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int[] nextGreaterElements(int[] nums) {
            int[] res = new int[nums.length];
            Arrays.fill(res, -1);
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            for (int i = 1; i < 2 * nums.length; i++) {
                while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek() % nums.length]) {
                    Integer popIdx = stack.pop();
                    res[popIdx % nums.length] = nums[i % nums.length];
                }
                stack.push(i);
            }

            return res;
        }

        public int[] nextGreaterElements2(int[] nums) {
            // 注意题目中的要求：-10的 9 次方 <= nums[i] <= 10 的 9 次方

            int[] res = new int[nums.length];
            boolean[] flag = new boolean[nums.length]; // 存在更大元素的标记位

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            for (int i = 1; i < 2 * nums.length; i++) {
                while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek() % nums.length]) {
                    Integer popIdx = stack.pop();
                    if (!flag[popIdx % nums.length]) {
                        res[popIdx % nums.length] = nums[i % nums.length];
                        flag[popIdx % nums.length] = true;
                    }
                }
                stack.push(i);
            }

            // 这时对于“无下一个更大元素”的，在 res 中还是 0，需要都替换成-1. 注意题目中的要求：-10的 9 次方 <= nums[i] <= 10 的 9 次方
            for (int i = 0; i < res.length; i++) {
                if (!flag[i]) {
                    res[i] = -1;
                }
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}