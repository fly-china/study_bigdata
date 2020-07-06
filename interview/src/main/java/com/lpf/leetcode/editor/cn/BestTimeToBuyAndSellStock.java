//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。 
//
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
// Related Topics 数组 动态规划
// no.121

package com.lpf.leetcode.editor.cn;

import java.util.Objects;

/**
 * 思路： 最大利润 =  max（前几天最大利润， 今日价格 - 前几天最低价格）
 * 找波谷之后最高的波峰
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
//        int[] prices = {7,1,5,3,6,4};
//        int[] prices = {77,6,4,3,1};
        int[] prices = {7, 2, 5, 1, 3};
        int maxProfit = solution.maxProfit(prices);
        System.out.println("最大利润为：" + maxProfit);

    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if (Objects.isNull(prices) || prices.length < 2) {
                return 0;
            }

            int maxProfit = 0;
            int minPrice = Integer.MAX_VALUE;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minPrice) {
                    minPrice = prices[i];
                } else {
                    if (prices[i] - minPrice > maxProfit) {
                        maxProfit = prices[i] - minPrice;
                    }
                }

            }

            return maxProfit;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
class Solution_B {
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

}