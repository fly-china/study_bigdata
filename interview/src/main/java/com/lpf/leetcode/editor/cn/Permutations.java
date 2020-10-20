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
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
        int[] nums = {1,2,3};
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println("全排列数量：" + lists.size());
        for (List<Integer> list : lists) {
            list.forEach(System.out::print);
            System.out.println("");
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            // TODO
            List<List<Integer>> res = new ArrayList<>();
            if (nums != null && nums.length > 0) {
                backtrack(nums, 0, res, new ArrayList<Integer>());
            }

            return res;
        }

        private void backtrack(int[] nums, int index, List<List<Integer>> res, List<Integer> elemList) {
            if (nums.length == index) {
                res.add(elemList);
                return;
            }

            elemList.add(nums[index]);
            backtrack(nums, index + 1, res, elemList);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}