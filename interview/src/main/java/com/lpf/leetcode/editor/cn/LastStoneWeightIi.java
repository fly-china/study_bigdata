//有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。 
//
// 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下： 
//
// 
// 如果 x == y，那么两块石头都会被完全粉碎； 
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。 
// 
//
// 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [2,7,4,1,8,1]
//输出：1
//解释：
//组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
//组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
//组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
//组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
// 
//
// 示例 2： 
//
// 
//输入：stones = [31,26,33,21,40]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= stones.length <= 30 
// 1 <= stones[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 927 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [1049]-最后一块石头的重量 II
 */
public class LastStoneWeightIi {
    public static void main(String[] args) {
        Solution solution = new LastStoneWeightIi().new Solution();
        System.out.println(solution.lastStoneWeightII(new int[]{1, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1、dp[i] : 在 0-i 课石头中任意匹配石头，剩余的最小重量是 dp[i]
         * 2、dp[i] = Math.min(Math.abs(stones[i] - dp[i-1]), dp[i])
         * 3、dp[0] = 0;
         * <p>
         * 本题的核心解题思路在于：将一波石头分成重量尽可能接近的两波石头，两波石头的重量差就是结果。假设分成大小为 x,y 两堆，y>=x
         * x + y = sum;  y - x = res   ---> sum-2x = res
         * 要使最后一块石头的重量尽可能地小，x 需要在不超过 ⌊sum/2⌋ 的前提下尽可能地大。因此本问题可以看作是背包容量为 ⌊sum/2⌋，物品重量和价值均为 stones 的 0-1 背包问题。
         * <p>
         * 1、dp[j]：在 0-i 课石头中任意匹配石头，获得的最大价值是 dp[j]
         * 2、dp[j] = Math.max(dp[j-stones(i)] + stones(i), dp[j])
         * 3、dp[0] = 0
         * 4、从大到小遍历
         */
        public int lastStoneWeightII(int[] stones) {
            int sum = Arrays.stream(stones).sum();
            int target = sum / 2;

            int[] dp = new int[target + 1];
            dp[0] = 0;

            for (int i = 0; i < stones.length; i++) {
                // 为什么 j >= stones[i] ？ 因为背包重量 j < stones[i] 时，说明装不下 i，那么 dp[i] 是等于 dp[i-1] 的，而 dp[i-1] 又已经被计算过
                for (int j = target; j >= stones[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }
            }

            return sum - 2 * dp[target];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}