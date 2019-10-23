//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
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
// no.122

package com.lpf.leetcode.editor.cn;

import java.util.Objects;

public class BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIi().new Solution();
        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {77,6,4,3,1};
//        int[] prices = {7, 2, 5, 1, 3};
        int maxProfit = solution.maxProfit(prices);
        System.out.println("最大利润为：" + maxProfit);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：每一个波谷之后的波峰，作为一次购买和售出的时机，获得一次利润的机会
         */
//        public int maxProfit(int[] prices) {
//            if (Objects.isNull(prices) || prices.length < 2) {
//                return 0;
//            }
//
//            int i = 0;
//            int sumProfit = 0;
//            int valley = 0; // 波谷
//            int peek = 0; // 波峰
//            while (i < prices.length - 1) {
//                while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
//                    // 下划线
//                    i++;
//                }
//                valley = prices[i];
//                while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
//                    i ++;
//                }
//                peek = prices[i];
//
//                sumProfit += peek - valley;
//            }
//
//            return sumProfit;
//        }

        /**
         * 只要今天比昨天价格高，就算持有股票增值，存在利润空间。可计入最高利润。
         * 直到今天价格 < 昨天价格，说明股票再跌，此时手中不持有股票，才不会影响现有利润
         */
        public int maxProfit(int[] prices) {
            if (Objects.isNull(prices) || prices.length < 2) {
                return 0;
            }

            int sumProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    sumProfit += prices[i] - prices[i - 1];
                }

            }

            return sumProfit;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}