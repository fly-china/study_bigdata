// 小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。他需要带一些研究材料，但是他的行李箱空间有限。这些研究材料包括实验设备、文献资料和实验样本等等，它们各自占据不同的空间，并且具有不同的价值。
// 小明的行李空间为 N，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料只能选择一次，并且只有选与不选两种选择，不能进行切割。
//
// 输入描述
// 第一行包含两个正整数，第一个整数 M 代表研究材料的种类，第二个正整数 N，代表小明的行李空间。
// 第二行包含 M 个正整数，代表每种研究材料的所占空间。
// 第三行包含 M 个正整数，代表每种研究材料的价值。
//
// 输出描述
// 输出一个整数，代表小明能够携带的研究材料的最大价值
//
// 输入示例
// 6 1
// 2 2 3 1 5 2
// 2 3 1 5 4 3
// 输出：5
//
// 输入示例
// 3 4
// 1  3  4
// 15 20 30
// 输出：35

package com.lpf.leetcode.dp;

import com.lpf.leetcode.editor.cn.IntegerBreak;

/**
 * 1、确定 dp 数组以及下标的含义
 * 2、确定递推公式（状态转移方程）
 * 3、初始化
 * 4、遍历顺序
 * 5、调试
 *
 * @author lipengfei
 * @create 2024-12-13 10:52
 **/
public class Backpack01Solution {
    public static void main(String[] args) {
        Backpack01Solution.Solution solution = new Backpack01Solution().new Solution();
        int w = 4;
        int[] weights = {1, 3, 4};
        int[] costs = {15, 20, 30};

//        int w = 5;
//        int[] weights = {2, 2, 3, 1, 5, 2};
//        int[] costs   = {2, 3, 1, 5, 4, 3};

        System.out.println("maxBackpackVal_1, result=" + solution.maxBackpackVal(w, weights, costs));
        System.out.println("maxBackpackVal_2, result=" + solution.maxBackpackVal2(w, weights, costs));
        System.out.println("maxBackpackVal_3, result=" + solution.maxBackpackVal3(w, weights, costs));

    }

    class Solution {

        /**
         * 时间和空间复杂度均为 O(n*w)
         *
         * @param w       背包的空间大小
         * @param weights 物品的重量
         * @param costs   物品的价值
         * @return 背包能够装下的最大价值
         */
        public int maxBackpackVal(int w, int[] weights, int[] costs) {
            /**
             * 1、dp[i][j] : 在前 0-i 件商品中任取 n 件，装入到容量为 j 的背包中，能达到的最大价值。注意每个商品只有一件，只有选或不选两种选择
             * <p>
             * 2、确定递推公式，思路：对于物品 i 有两种选择，选 or 不选
             * -- a. 不选 i：相当于只有前 i-1 件物品，背包容量相同的子问题，即  val = dp[i-1][j]
             * -- b. 选择 i：相当于只有前 i-1 件物品，背包容量扣除 weight[i] 后的子问题，即 val = dp[i-1][j-w(i)] + cost[i]
             * -- c. 在上述两种情况中找到 max 的一方。 dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w(i)] + cost[i])
             * <p>
             * 3、初始化
             * -- 初始化【列】：背包容量为 0 时，dp[i][0] = 0;
             * -- 初始化【行】：物品 0 待选时，dp[0][j] = if(j > w[0]) dp[0][j] = cost[0];
             * <p>
             * 4、遍历顺序：从小到大
             */
            int nums = weights.length;
            int[][] dp = new int[nums + 1][w + 1];

            // 初始化列。其实可以省略，数组中默认值是 0
            for (int i = 0; i < nums; i++) {
                dp[i][0] = 0;
            }

            // 初始化行
            for (int j = 0; j <= w; j++) {
                if (j >= weights[0]) {
                    dp[0][j] = costs[0];
                }
            }

            // 遍历
            for (int i = 1; i < nums; i++) { // 遍历物品。物品 0 那一行已经被初始化，所以从 1 开始
                for (int j = 1; j <= w; j++) { // 遍历背包重量。首列已经被初始化，从 1 开始
                    if (j < weights[i]) { // 如果背包的重量 j 都不足以放下物品 i。那结果就等同于上一行的数据。
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], (dp[i - 1][j - weights[i]] + costs[i]));
                    }
                    System.out.println("dp[" + i + "][" + j + "]=" + dp[i][j]);
                    // dp[1][1]=15 dp[1][2]=15  dp[1][3]=20  dp[1][4]=35
                    // dp[2][1]=15 dp[2][2]=15  dp[2][3]=20  dp[2][4]=35
                }
            }

            return dp[nums - 1][w];
        }


        /**
         * 在上述状态转移方程以及遍历中会发现，第 i 行的数据只依赖 i-1 行的数据，前面的行都不需要了。
         * 那么，是不是 dp 数组的行数有 2 行就够了，滚动使用 ？
         * <p>
         * 时间复杂度均为 O(n*w),空间复杂度 O(2w)
         */
        public int maxBackpackVal2(int w, int[] weights, int[] costs) {
            int nums = weights.length;
            int[][] dp = new int[2][w + 1];

            // 初始化行
            for (int j = 0; j <= w; j++) {
                if (j >= weights[0]) {
                    dp[0][j] = costs[0];
                }
            }

            int pre = 0; // 上一行的 dp 数据
            int cur = 1; // 本行正在处理的 dp 数据
            int tmp; // 临时值

            for (int i = 1; i < nums; i++) { // 遍历物品
                for (int j = 1; j <= w; j++) { // 遍历背包
                    if (j < weights[i]) { // 背包可称重量小于 weight[i]
                        dp[cur][j] = dp[pre][j]; // 等同于背包重量相同，i-1 物品的 dp 值
                    } else {
                        dp[cur][j] = Math.max(dp[pre][j], (dp[pre][j - weights[i]] + costs[i]));
                    }
//                    System.out.println("dp[" + cur + "][" + j + "]=" + dp[cur][j]);
                }

                // 滚动交换 pre 和 cur
                tmp = pre;
                pre = cur;
                cur = tmp;
            }

            // 物品数量为偶数时，使用 pre。因为在上述外层循环中，最后对 pre 和 cur 做了交换。
            return dp[nums % 2 == 1 ? cur : pre][w];
        }

        /**
         * maxBackpackVal2 中使用两行数组交替赋值；并且在 for 循环中 j < weights[i] 时，都有 dp[i][j] = dp[i-1][j]。
         * 可以抽象为：一张二维的表格 dp[i][j]，那么每个格子在转移时，只会用到上一行中在它左侧的格子
         * 我们调整一下转移的顺序，每一行从右往左进行更新（从大到小），那么 「“活跃”」 的格子就正好只有「上一行的左半部分以及这一行的右半部分」
         * <p>
         * dp[i] = Math.max(dp[i-1], dp[j-w(i)] + costs[i])
         * 遍历顺序：从右到左，否则会被覆盖或者说重复取物品（变成完全背包问题）
         */
        public int maxBackpackVal3(int w, int[] weights, int[] costs) {
            int[] dp = new int[w + 1];
            dp[0] = 0;

            // 初始化行
//            for (int j = 0; j <= w; j++) {
//                if (j >= weights[0]) {
//                    dp[j] = costs[0];
//                }
//            }

            for (int i = 0; i < weights.length; i++) {
                for (int j = w; j >= weights[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + costs[i]);
                    System.out.println("dp[" + j + "]=" + dp[j]);
                }
            }

            return dp[w];
        }

    }
}
