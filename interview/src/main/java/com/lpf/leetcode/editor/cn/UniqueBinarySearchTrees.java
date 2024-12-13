//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 2585 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [96]-不同的二叉搜索树
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new UniqueBinarySearchTrees().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        /**
         * dp[0] = 1(空子树也是一种形态)；dp[1]=1; dp[2]=2。 当 i=3 时，共有多少种形态？
         * 思路：分别以 1、2、3 为头节点。
         * - 以 1 为头节点，那么 2、3 肯定在右子树，左子树为空。 f(1) = f(0) * f(2) = 1*2 = 2
         * - 以 2 为头节点，那么 1 左子树，3在右子树。 f(2) = f(1) * f(1) = 1*1 = 1
         * - 以 3 为头节点，那么 1、2 肯定在左子树，右子树为空。 f(3) = f(2) * f(0) = 2*1 = 2
         * 所以 dp[3] = f(1)+f(2)+f(3) = 5 种。
         * <p>
         * dp[i], 就分别以 1、2、3 ... i 为树的头节点，再将每个头节点对应的种树做累加。
         * -- 以 j 为头节点时（1 <= j <= i）, 树左侧有 j-1 个节点；树的右侧有 i - j 个节点。
         * -- 那么，左侧有 dp[j-1] 种形态，右侧有 dp[i-j] 种形态。并起来就有 dp[j-1] * dp[i-j] 种形态。
         * -- 头节点 j 从 1遍历到 i，则共有 dp[i] += dp[j-1] * dp[i-j] 种形态
         * <p>
         * <p>
         * <p>
         * 1、dp[i] : i 个节点的二叉搜索树有多少种形态
         * 2、状态转移方程：dp[i] += dp[j-1] * dp[i-j]
         * 3、dp[0] = 1；dp[1]=1
         * 4、从小到大遍历
         */
        public int numTrees(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
//            dp[1] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i] += dp[j - 1] * dp[i - j];
                    // dp[1] += dp[0] * dp[0]
                }
            }

            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}