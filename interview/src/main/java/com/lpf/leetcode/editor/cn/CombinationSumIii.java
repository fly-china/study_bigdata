//找出所有相加之和为 n 的 k 个数的组合，且满足下列条件： 
//
// 
// 只使用数字1到9 
// 每个数字 最多使用一次 
// 
//
// 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。 
//
// 
//
// 示例 1: 
//
// 
//输入: k = 3, n = 7
//输出: [[1,2,4]]
//解释:
//1 + 2 + 4 = 7
//没有其他符合的组合了。 
//
// 示例 2: 
//
// 
//输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
//解释:
//1 + 2 + 6 = 9
//1 + 3 + 5 = 9
//2 + 3 + 4 = 9
//没有其他符合的组合了。 
//
// 示例 3: 
//
// 
//输入: k = 4, n = 1
//输出: []
//解释: 不存在有效的组合。
//在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
// 
//
// 
//
// 提示: 
//
// 
// 2 <= k <= 9 
// 1 <= n <= 60 
// 
//
// Related Topics 数组 回溯 👍 893 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * [216]-组合总和 III
 */
public class CombinationSumIii {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIii().new Solution();
        List<List<Integer>> lists = solution.combinationSum3(3, 17);
        System.out.println(JSONObject.toJSON(lists));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<List<Integer>> res = new ArrayList<>();
        private LinkedList<Integer> path = new LinkedList<>();
        private int sum = 0;

        public List<List<Integer>> combinationSum3(int k, int n) {
            backtracking(n, k, 1);
            return res;
        }

        private void backtracking(int targetSum, int k, int startIdx) {
            if (sum > targetSum || path.size() > k) {
                return;
            }
            if (path.size() == k) {
                if (sum == targetSum) {
                    res.add(new ArrayList<>(path));
                }
                return;
            }

            for (int i = startIdx; i <= 9; i++) {
                path.addLast(i);
                sum += i;
                backtracking(targetSum, k, i + 1);
                Integer removeLast = path.removeLast();
                sum -= removeLast;

            }
        }
    }


//leetcode submit region end(Prohibit modification and deletion)


}