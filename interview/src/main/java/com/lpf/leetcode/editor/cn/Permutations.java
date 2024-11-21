//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 956 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * [46]-全排列
 * 优质答案分析：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 *
 * @author lipengfei
 * @date 2020-10-20 11:29:24
 **/
public class Permutations {

    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println("全排列数量：" + lists.size());
        for (List<Integer> list : lists) {
            list.forEach(System.out::print);
            System.out.println("");
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<List<Integer>> res = new ArrayList<>();
        private LinkedList<Integer> path = new LinkedList<>();
        private boolean[] usedFlag;

        public List<List<Integer>> permute(int[] nums) {

            usedFlag = new boolean[nums.length];
            backtracking(nums, 0);
            return res;
        }

        private void backtracking(int[] nums, int idx) {
            if (path.size() >= nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (usedFlag[i]) {
                    continue;
                }

                path.add(nums[i]);
                usedFlag[i] = true;
                backtracking(nums, i);
                usedFlag[i] = false;
                path.removeLast();
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        public List<List<Integer>> permute(int[] nums) {

            List<List<Integer>> res = new ArrayList<>();
            if (nums != null && nums.length > 0) {
                // 对数组中对应index的数字，标记是否被使用过
                boolean[] useFlag = new boolean[nums.length];
                backtrack(nums, 0, res, new ArrayList<Integer>(), useFlag);
            }

            return res;
        }

        /**
         * @param nums     数组
         * @param depth    已经使用的数字数量
         * @param res      最终结果集
         * @param elemList 组合-集合
         * @param useFlag  标记数组
         */
        private void backtrack(int[] nums, int depth, List<List<Integer>> res, List<Integer> elemList, boolean[] useFlag) {
            if (nums.length == depth) {
                res.add(new ArrayList<>(elemList));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (!useFlag[i]) {
                    // 该数字未经被使用过，进入下述逻辑

                    // 1、做选择
                    // 标记该数字已被使用
                    useFlag[i] = true;
                    elemList.add(nums[i]);

                    // 2、backtrack(路径, 选择列表)
                    backtrack(nums, depth + 1, res, elemList, useFlag);

                    // 3、撤销逻辑
                    useFlag[i] = false; // 撤销逻辑
                    elemList.remove(elemList.size() - 1);// 从数组中移除
                }

            }
        }
    }


    class Solution_SLOW {

        //
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums != null && nums.length > 0) {
                backtrack(nums, 0, res, new ArrayList<Integer>());
            }

            return res;
        }

        /**
         * 省略了boolean[] useFlag数组进行的标记, 但是使用elemList.contains(nums[i])判断，影响效率
         *
         * @param nums     数组
         * @param depth    已经使用的数字数量
         * @param res      最终结果集
         * @param elemList 组合-集合
         */
        private void backtrack(int[] nums, int depth, List<List<Integer>> res, List<Integer> elemList) {
            if (nums.length == depth) {
                res.add(new ArrayList<>(elemList));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (!elemList.contains(nums[i])) {
                    // 该数字未经被使用过，进入下述逻辑

                    elemList.add(nums[i]);
                    backtrack(nums, depth + 1, res, elemList);
                    elemList.remove(elemList.size() - 1);
                }

            }
        }
    }

}