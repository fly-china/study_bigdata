//给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。 
//
// 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,6,7,7]
//输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,4,3,2,1]
//输出：[[4,4]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 15 
// -100 <= nums[i] <= 100 
// 
//
// Related Topics 位运算 数组 哈希表 回溯 👍 825 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.*;

/**
 * [491]-非递减子序列
 */
public class NonDecreasingSubsequences {
    public static void main(String[] args) {
        Solution solution = new NonDecreasingSubsequences().new Solution();
        solution.findSubsequences(new int[]{4, 6, 7, 7});

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<List<Integer>> res = new ArrayList<>();
        private LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            if (nums != null && nums.length > 1) {
                backtracking(nums, 0);
            }
            return res;
        }

        private void backtracking(int[] nums, int idx) {
            // 出现符合条件的就进行收集，而不是到叶子节点收集
            if (path.size() >= 2) {
                res.add(new ArrayList<>(path));
            }

            if (idx >= nums.length) return;

            Set<Integer> usedSet = new HashSet<>();
            for (int i = idx; i < nums.length; i++) {
                // 树中的同一层进行去重。因为是无序的数组，所以不能使用: i > idx && nums[i] == nums[i - 1]
                if (usedSet.contains(nums[i])) {
                    continue;
                }

                // 如果当前元素 < 已收集的最后元素，则不满足“非递减”条件，继续向后遍历
                if (!path.isEmpty() && path.peekLast() > nums[i]) {
                    continue;
                }

                usedSet.add(nums[i]); // 对于它，只需要add，不需要 remove，因为每一层都会生成一个新 set
                path.add(nums[i]);
                backtracking(nums, i + 1);
                path.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}