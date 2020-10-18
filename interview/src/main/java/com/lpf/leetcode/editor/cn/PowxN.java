//实现 pow(x, n) ，即计算 x 的 n 次幂函数。 
//
// 示例 1: 
//
// 输入: 2.00000, 10
//输出: 1024.00000
// 
//
// 示例 2: 
//
// 输入: 2.10000, 3
//输出: 9.26100
// 
//
// 示例 3: 
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25 
//
// 说明: 
//
// 
// -100.0 < x < 100.0 
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。 
// 
// Related Topics 数学 二分查找 
// 👍 517 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [50]-Pow(x, n)
 */
public class PowxN {
    public static void main(String[] args) {
        Solution solution = new PowxN().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 快速幂等法
         * 2^10 = 2^5 * 2^5
         * 2^5 = 2^2 * 2^2 *2
         */
        public double myPow(double x, int n) {
            if (x == 0) return 0; // x为0时，永为0
            if (x == 1) return 1;// x为1时，永为1
            if (n == 0) return 1; // n为1时，永为1
            if (n == Integer.MIN_VALUE) return 1.0 / (myPow(x, -(n + 1)) * x);

            double res = 1.0d;
            if (n < 0) {
                x = 1 / x;
                n = -n;
            }

            while (n > 0) {
                if ((n & 1) == 1) {
                    res *= x;
                }
                x *= x;
                n >>= 1;
            }


            return res;
        }

        /**
         * 2^10 = 2^5 * 2^5
         * 2^5 = 2^2 * 2^2 *2
         */
        public double myPow_A(double x, int n) {
            if (x == 0) return 0; // x为0时，永为0
            if (x == 1) return 1;// x为1时，永为1
            if (n == 0) return 1; // n为1时，永为x
            // 考虑Integer.MIN_VALUE直接取反，会溢出
            if (n == Integer.MIN_VALUE) return 1.0 / (myPow(x, -(n + 1)) * x);
            if (n < 0) return 1.0 / myPow(x, -n);

            double subres = myPow(x, n / 2);

            return n % 2 == 0 ? subres * subres : subres * subres * x;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


}