//如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。 
//
// 字母和数字都属于字母数字字符。 
//
// 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "A man, a plan, a canal: Panama"
//输出：true
//解释："amanaplanacanalpanama" 是回文串。
// 
//
// 示例 2： 
//
// 
//输入：s = "race a car"
//输出：false
//解释："raceacar" 不是回文串。
// 
//
// 示例 3： 
//
// 
//输入：s = " "
//输出：true
//解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
//由于空字符串正着反着读都一样，所以是回文串。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2 * 10⁵ 
// s 仅由可打印的 ASCII 字符组成 
// 
//
// Related Topics 双指针 字符串 👍 781 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [125]-验证回文串
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
        System.out.println(solution.isPalindrome("   "));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 本质考察的还是如何判断字符串是否回文，本题只是需要额外处理符号和大小写字母
        // 常规思路是：先通过一次遍历，把特殊字符去掉并把大写转小写，然后通过双指针判断回文
        // 进阶思路是：一次遍历搞定。双指针边遍历边判断，遇到特殊字符时当场处理
        public boolean isPalindrome(String s) {
            if (s == null) return false;

            int start = 0;
            int end = s.length() - 1;
            while (start < end) {
                // 1、从左到右，先找到左侧第一个有效字符
                while (start < end && !Character.isLetterOrDigit(s.charAt(start))) {
                    start++;
                }
                // 2、从右到左，先找到右侧第一个有效字符
                while (start < end && !Character.isLetterOrDigit(s.charAt(end))) {
                    end--;
                }

                if (start > end || Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }

                start++;
                end--;
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}