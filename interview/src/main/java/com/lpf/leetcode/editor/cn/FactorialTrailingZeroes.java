//给定一个整数 n，返回 n! 结果尾数中零的数量。 
//
// 示例 1: 
//
// 输入: 3
//输出: 0
//解释: 3! = 6, 尾数中没有零。 
//
// 示例 2: 
//
// 输入: 5
//输出: 1
//解释: 5! = 120, 尾数中有 1 个零. 
//
// 说明: 你算法的时间复杂度应为 O(log n) 。 
// Related Topics 数学


package com.lpf.leetcode.editor.cn;

/**
 * [172]-阶乘后的零
 */
public class FactorialTrailingZeroes {
    public static void main(String[] args) {
        Solution solution = new FactorialTrailingZeroes().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 此问题，在于找10(2*5)的个数,
         * 每出现一个5，必然已经出现1个2。2的个数必然大于5的个数。
         * 所以，可以转化为：找5的个数
         */
        public int trailingZeroes(int n) {
            if (n < 5) {
                return 0;
            }
            return (n / 5) + trailingZeroes(n / 5);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}