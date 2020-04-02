//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
// 输出: 7
// 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
// 输出: 4
// 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
// Related Topics 贪心算法 数组

package com.lpf.leetcode.editor.cn;

/**
 * 122. 买卖股票的最佳时机 II
 * <p>
 * 思路：
 * 贪心算法，只要price[i+1] - price[i] > 0，便计入利润。
 * 即：只要当天价格 大于 前一天的价格，利润便存在。累加这些利润便是最大利润。（但并不意味破坏买入、卖出的规则，只是模型上符合）
 * 如：数组[1,2,3,4,5]，形成的最大利润为：（2-1）+（3-2）+（4-3）+（5-4）=4
 * <p>
 * 有人存在疑惑：对于价格曲线存在波峰波谷这种，是否跨越多个波谷波峰的会形成更大的利润
 * 其实不是，因为试图通过考虑差异较大的点以获取更多的利润，获得的净利润总是会小与包含它们而获得的静利润.
 * 因为C总是小于A+B,也因为跨过1+的价格波峰拐点，是比带来了股票的亏损。
 */
public class BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIi().new Solution();

    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            if (prices.length < 2) {
                return maxProfit;
            }

            for (int i = 1; i < prices.length; i++) {
                if ((prices[i] - prices[i - 1]) > 0) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }

            return maxProfit;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}