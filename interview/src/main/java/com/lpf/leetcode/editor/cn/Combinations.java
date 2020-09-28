//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入:n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 398 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
        List<List<Integer>> combine = solution.combine(4, 2);
//        System.out.println(JSONObject.toJSON(combine));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * f（10,3）= f(9,3) + f（9,2）...10
         * f（4,2）= f(3,2) + f（3,1）...4
         * f（3,2）= f(2,2) + f(2,1)...3
         * f(2,2)
         */
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> list = new ArrayList<>();
            backtrack(list, n, k, 1, new ArrayList<>());
            return list;
        }

        private void backtrack(List<List<Integer>> list, int n, int k, int start, List<Integer> tempList) {
            //终止条件，找到一对组合
            if (k == 0) {
                list.add(new ArrayList<>(tempList));
                return;
            }
            //注意这里的i不能从0开始，如果从0开始会出现重复的，比如[1，2]和[2，1]
            for (int i = start; i <= n - k + 1; i++) {
                //把当前值添加到集合中
                tempList.add(i);
                //递归调用，继续下一个分支
                backtrack(list, n, k - 1, i + 1, tempList);
                //从当前分支跳到下一个分支的时候要把之前添加的值给移除
                System.out.println( tempList.toString());
                tempList.remove(tempList.size() - 1);
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution_RECUR {
        /**
         * f（10,3）= f(9,3) + f（9,2）...10
         * f（4,2）= f(3,2) + f（3,1）...4
         * f（3,2）= f(2,2) + f(2,1)...3
         * f(2,2)
         */
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> resList = new ArrayList<>();
            // 递归出口
            if (k <= 0 || n < k) return resList;


            // 1、选n时，从n-1中选择k-1个数字，在拼接上n
            // 1.1、从n-1中选择k-1个数字
            resList = combine(n - 1, k - 1);
            // 1.2、若结果集为空，则新建一个结果集
            if (resList.isEmpty()) {
                List<Integer> partList = new ArrayList<>();
                resList.add(partList);
            }
            // 1.3、在每个结果集上，拼接n
            for (List<Integer> list : resList) {
                list.add(n);
            }

            // 2、不选n时，从n-1中选择k个数字
            resList.addAll(combine(n - 1, k));
            return resList;
        }
    }
}