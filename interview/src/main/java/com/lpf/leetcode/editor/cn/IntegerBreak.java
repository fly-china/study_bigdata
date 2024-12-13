//给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。 
//
// 返回 你可以获得的最大乘积 。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。 
//
// 示例 2: 
//
// 
//输入: n = 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
//
// 
//
// 提示: 
//
// 
// 2 <= n <= 58 
// 
//
// Related Topics 数学 动态规划 👍 1430 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [343]-整数拆分
 */
public class IntegerBreak {
    public static void main(String[] args) {
        Solution solution = new IntegerBreak().new Solution();
        System.out.println(solution.integerBreak(10));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 1、dp[i]：分拆数字i，可以得到的最大乘积为dp[i]
         * 2、本题需要将 i 进行拆分，从 1 开始拆直到 i，在所有的拆分项中记录 max 值。 计算完 dp[i], 再去计算 dp[i+1],所以会有两重 for 循环
         * 拆分时，可以拆分成两个数，三个数及更多 : dp[i] = Math.max(dp[i],  j * (i-j), j * dp[i-j])
         * 不需要：dp[j] * dp[i-j] , 因为在更早的循环中，其实已经遍历过了。假如：j 还能拆成 m+n, m、n 均小于 j
         * dp[j] * dp[i-j] = m * n * dp[i-j] = m * (n * dp[i-j])   他其实已经是 m * dp[i-m] 的子集之一了
         * <p>
         * 3、题目中限定 n>=2; dp[2] = 1
         * 4、dp[i] 依赖 dp[i-1], 所以从小到大遍历
         */
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[2] = 1;

            for (int i = 3; i <= n; i++) { // 从 3 -> n 开始计算 dp[i]
//                for (int j = 1; j < i; j++) { // 将 i 从 1 开始拆分，寻找最大值
                for (int j = 1; j <= i/2; j++) { // 将 i 从 1 开始拆分，寻找最大值
                    dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                    System.out.println("dp[" + i + "]=" + dp[i]);
                }
            }

            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}