//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划
// no.70题

package com.lpf.leetcode.editor.cn;

public class ClimbingStairs {

    /**
     * f(n) = f(n-1) + f(n-2)
     * f(1) = 1， f(2) = 2
     * 转化为：
     */
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
        int nums = solution.climbStairs(4);
        System.out.println("共有" + nums + "种方法");

    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 使用 dp 解法：
         * 1、确定 dp 数组及下标的含义
         * - dp[i]: 达到第 i 台阶，有多少种跳法
         * 2、确定递推公式（又叫状态转移方程）
         * - dp[i] = dp[i-1] + dp[i-2]  即：到达 i 有两种途径，一种是从 i-1 跳一步，另一种是从 i-2 跳两步
         * 3、数组初始化
         * - dp[1] = 1; dp[2] = 2
         * 4、确定遍历顺序。
         * - dp[i] = dp[i-1] + dp[i-2], 依赖较小的，所以从小到大遍历
         * 5、举例推导dp数组
         * 1 2 3 5 8 13 21 34 55
         *
         * @param n
         * @return
         */
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        public int climbStairs2(int n) {
//            if (n <= 0) {
//                return 0;
//            }
//            if (n == 1 || n == 2) {
//                return n;
//            }
//
//            // 一、效率最慢的解法
////            return climbStairs(n - 1) + climbStairs(n - 2);

            // 二、解法2
//            Map<Integer, Integer> climbMap = new HashMap<>(n);
//            climbMap.put(1, 1);
//            climbMap.put(2, 2);
//            for (int i = 3; i < n; i++) {
//                climbMap.put(i, climbMap.get(i - 1) + climbMap.get(i - 2));
//            }
//
//            return climbMap.get(n - 1) + climbMap.get(n - 2);
            if (n == 1) {
                return 1;
            }
            int first = 1;
            int second = 2;
            for (int i = 3; i <= n; i++) {
                int third = first + second;
                first = second;
                second = third;
            }
            return second;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}