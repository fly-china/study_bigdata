//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用 一次 。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
//
// Related Topics 数组 回溯 👍 1597 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * [40]-组合总和 II
 */
public class CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIi().new Solution();
        int[] candidates = {1, 1, 2, 3};
        List<List<Integer>> lists = solution.combinationSum2(candidates, 3);
        System.out.println(JSONObject.toJSON(lists));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<List<Integer>> res = new ArrayList<>();
        private LinkedList<Integer> path = new LinkedList<>();
        private int sum = 0;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            if (candidates != null && candidates.length >= 1) {
                Arrays.sort(candidates);
                backtracking(candidates, target, 0);
            }
            return res;
        }

        private void backtracking(int[] candidates, int target, int idx) {
            if (sum > target) return;
            if (sum == target) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = idx; i < candidates.length && sum + candidates[i] <= target; i++) {
                String msg = "idx=" + idx + "--i=" + i + "---c[i]=" + candidates[i];
                if (i > 0) {
                    msg += "--c[i-1]=" + candidates[i - 1];
                }
                System.out.println(msg);
                // 正确剔除重复解的办法,跳过同一树层使用过的元素
                if (i > idx && candidates[i] == candidates[i - 1]) {
                    System.out.println("continue");
                    continue;
                }
                path.add(candidates[i]);
                sum += candidates[i];
                backtracking(candidates, target, i + 1);
                sum -= candidates[i];
                path.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}