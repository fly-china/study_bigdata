//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法 
// 👍 499 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [47]-全排列 II
 *
 * @author lipengfei
 * @date 2020-10-21 11:29:24
 **/
public class PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
        int[] nums = {1, 1, 2};
        List<List<Integer>> res = solution.permuteUnique(nums);
        System.out.println("全排列组合的数量：" + res.size());
        res.forEach(System.out::println);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 和46题相比，解题思路重点关注三个点：
         * 1、画出递归树，找规律，那就很简单了
         * 2、重点代码一：依赖于重复数的判断，所以主函数中先对数组排序 Arrays.sort(nums);
         * 3、重点代码二：依赖于重复数的判断，所以回溯函数中判断时，要判断：本次遍历到的数字是否和上一个数字相同，且上一个数字是否正在被使用。
         *
         * ** 没想到和官方题解代码一模一样 **
         */
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums != null && nums.length > 0) {
                // 先进性排序
                Arrays.sort(nums);
                boolean[] useFlag = new boolean[nums.length];
                backtrack(nums, useFlag, 0, res, new ArrayList<>());
            }

            return res;
        }

        private void backtrack(int[] nums, boolean[] useFlag, int depth, List<List<Integer>> res, ArrayList<Integer> elemList) {
            if (depth == nums.length) {
                res.add(new ArrayList<>(elemList));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (!useFlag[i]) {
                    if (i > 0 && nums[i] == nums[i - 1] && !useFlag[i-1]) {
                        continue;
                    }

                    useFlag[i] = true;
                    elemList.add(nums[i]);

                    backtrack(nums, useFlag, depth + 1, res, elemList);

                    useFlag[i] = false;
                    elemList.remove(elemList.size() - 1);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}