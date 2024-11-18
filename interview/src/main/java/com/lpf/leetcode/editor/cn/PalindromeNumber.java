//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。 
//
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。 
//
// 
// 例如，121 是回文，而 123 不是。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 121
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3： 
//
// 
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= x <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？ 
//
// Related Topics 数学 👍 2922 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [9]-回文数
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new PalindromeNumber().new Solution();
        System.out.println(solution.isPalindrome(12321));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 通过纯数字的方式判断   1221、12321
        public boolean isPalindrome(int x) {
            // 小于 0 或 末位等于 0 的正数，均不是回文
            if (x < 0 || (x % 10 == 0 && x != 0)) {
                return false;
            }

            int revNum = 0; // 从末尾开始反转的数
            int num = x;    // 剩下的前面的数
            while (num > revNum) {
                // 当反转的数字 >= 前面的数字时，说明已经反转过半
                revNum = revNum * 10 + num % 10;
                num = num / 10;
            }

            return revNum == num || revNum / 10 == num;
        }


        // 数字转换为字符串，通过双指针的方式判断
        public boolean isPalindrome2(int x) {
            if (x < 0) return false;

            String str = x + "";
            int idx = 0;
            boolean isPalindrome = true;

            while (idx < (str.length() / 2)) {
                int endIdx = str.length() - idx - 1;
                if (str.charAt(idx) != str.charAt(endIdx)) {
                    isPalindrome = false;
                    break;
                }
                idx++;
            }

            return isPalindrome;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}