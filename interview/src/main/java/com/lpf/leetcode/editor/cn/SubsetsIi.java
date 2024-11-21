//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的 子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 位运算 数组 回溯 👍 1248 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * [90]-子集 II
 */
public class SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new SubsetsIi().new Solution();
        System.out.println(JSONObject.toJSON(solution.subsetsWithDup(new int[]{1, 2, 2})));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<List<Integer>> res = new ArrayList<>();
        private LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            backtracking2(nums, 0);
            return res;
        }

        private void backtracking2(int[] nums, int idx) {
            // 在树形结构中子集问题是要收集所有节点的结果，而组合问题是收集叶子节点的结果。
            res.add(new ArrayList<>(path));

            if (idx >= nums.length) return;

            Set<Integer> usedSet = new HashSet<>();
            for (int i = idx; i < nums.length; i++) {
                // 在同一层中，如果已经选过，后面就不需要了
                if (usedSet.contains(nums[i])) {
                    // 跳过本层中已经使用过的元素，后面的还要继续用，所以是 continue
                    continue;
                }

                usedSet.add(nums[i]);
                path.add(nums[i]);
                backtracking(nums, i + 1);
                path.removeLast();
            }

        }

        private void backtracking(int[] nums, int idx) {
            // 在树形结构中子集问题是要收集所有节点的结果，而组合问题是收集叶子节点的结果。
            res.add(new ArrayList<>(path));

            if (idx >= nums.length) return;

            for (int i = idx; i < nums.length; i++) {
                // 在同一层中，如果已经选过，后面就不需要了
                if (i > idx && nums[i] == nums[i - 1]) {
                    // 跳过本层中已经使用过的元素，后面的还要继续用，所以是 continue
                    continue;
                }
                path.add(nums[i]);
                backtracking(nums, i + 1);
                path.removeLast();
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}