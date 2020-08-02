//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31, 2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
// Related Topics 数学 
// 👍 2070 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * 如：1234，反转后为4321
 * int sum = 0;
 * 1：1234%10 = 4  1234/10=123  sum * 10 + 4 =4
 * 2：123%10  = 3  123/10 = 12  sum * 10 + 3 =43
 * 3：12%10   = 2  12/10  = 1   sum * 10 + 2 =432
 * 4：1%10    = 1  1/10   = 0   sum * 10 + 1 =4321
 * 结束
 * <p>
 * [7]-整数反转
 */
public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
//        System.out.println(solution.reverse(-1234));
//        System.out.println(solution.reverse(1234));
//        System.out.println(solution.reverse(120));
//        System.out.println("正数溢出：" + solution.reverse(2147483647));
        System.out.println("正数溢出：" + solution.reverse(1534236469));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int reverse(int x) {
            if (x == 0) return 0;

            int sum = 0;
            while (x != 0) {
                int tail = x % 10;
                int newRes = sum * 10 + tail;
                // 反向计算回去，如果不等于原值，说明已经溢出。如果数字溢出，直接返回0
                if ((newRes - tail) / 10 != sum)
                    return 0;
                sum = newRes;
                x = x / 10;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}