//假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
// 注意你不能在买入股票前卖出股票。
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 10^5 
//
// 
//
// 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-s
//tock/ 
// Related Topics 动态规划


package com.lpf.leetcode.editor.cn;

// [面试题63]-股票的最大利润

/**
 * 思路：最大利润 = Max(今天之前的最大利润, (今日股票金额 - 前N天中的最低股票金额))
 */
public class GuPiaoDeZuiDaLiRunLcof {
    public static void main(String[] args) {
        Solution solution = new GuPiaoDeZuiDaLiRunLcof().new Solution();

        int[] prices = new int[]{7,1,5,3,6,4};
        int[] prices2 = new int[]{7,6,4,3,1};

        System.out.println("最大利润为：" + solution.maxProfit(prices));
        System.out.println("最大利润为：" + solution.maxProfit(prices2));

    }

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int minPrice = Integer.MAX_VALUE;
            if (prices.length < 1) {
                return maxProfit;
            }

            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minPrice) {
                    minPrice = prices[i];
                    continue;
                }
                if ((prices[i] - minPrice) > maxProfit) {
                    maxProfit = prices[i] - minPrice;
                }
            }

            return maxProfit;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}