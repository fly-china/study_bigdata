//斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是： 
//
// 
//F(0) = 0，F(1) = 1
//F(n) = F(n - 1) + F(n - 2)，其中 n > 1
// 
//
// 给定 n ，请计算 F(n) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：1
//解释：F(2) = F(1) + F(0) = 1 + 0 = 1
// 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：2
//解释：F(3) = F(2) + F(1) = 1 + 1 = 2
// 
//
// 示例 3： 
//
// 
//输入：n = 4
//输出：3
//解释：F(4) = F(3) + F(2) = 2 + 1 = 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 30 
// 
//
// Related Topics 递归 记忆化搜索 数学 动态规划 👍 801 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [509]-斐波那契数
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        Solution solution = new FibonacciNumber().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 使用 dp 解法：
         * 1、确定 dp 数组及下标的含义
         * - dp[i]: 第 i 个数的斐波那契值
         * 2、确定递推公式（又叫状态转移方程）
         * - dp[i] = dp[i-1] + dp[i-2]
         * 3、数组初始化
         * - dp[0] = 0; dp[1]=1
         * 4、确定遍历顺序。
         * - dp[i]是依赖 dp[i - 1] 和 dp[i - 2]，那么遍历的顺序一定是从前到后遍历的
         * 5、举例推导dp数组
         * 按照这个递推公式dp[i] = dp[i - 1] + dp[i - 2]，我们来推导一下，当N为10的时候:0 1 1 2 3 5 8 13 21 34 55
         *
         * @param n
         * @return
         */
        public int fib(int n) {
            if (n <= 1)
                return n;
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}