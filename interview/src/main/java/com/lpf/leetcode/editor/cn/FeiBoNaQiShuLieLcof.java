//斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是： 
//
// 
//F(0) = 0，F(1) = 1
//F(n) = F(n - 1) + F(n - 2)，其中 n > 1
// 
//
// 给定 n ，请计算 F(n) 。 
//
// 答案需要取模 1e9+7(1000000007) ，如计算初始结果为：1000000008，请返回 1。 
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
// 0 <= n <= 100 
// 
//
// 
//
// Related Topics 记忆化搜索 数学 动态规划 👍 553 👎 0


package com.lpf.leetcode.editor.cn;

public class FeiBoNaQiShuLieLcof {
    public static void main(String[] args) {
        Solution solution = new FeiBoNaQiShuLieLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        /**
         * 1、确定dp数组以及下标的含义
         * dp[i] : 第i个斐波那契数值
         * <p>
         * 2、确定递推公式
         * dp[i] = dp[i-1] + dp[i-2]
         * <p>
         * 3、dp数组如何初始化
         * dp[0] = 0, dp[1] = 1
         * dp[2] = dp[1] + dp[0] = 1
         * dp[3] = dp[2] + dp[1] = 2
         * <p>
         * 4、确定遍历顺序
         * <p>
         * 5、举例推导dp数组
         *
         * @return
         */
        public int fib(int n) {
            if (n <= 1) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;

            for (int i = 2; i <= n; i++) {
                int tmp = dp[i - 1] + dp[i - 2];
                dp[i] = tmp % 1000000007;
            }

            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}