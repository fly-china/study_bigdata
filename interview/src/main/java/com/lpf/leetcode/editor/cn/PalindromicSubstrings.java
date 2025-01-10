//给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。 
//
// 回文字符串 是正着读和倒过来读一样的字符串。 
//
// 子字符串 是字符串中的由连续字符组成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 
//输入：s = "aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
//
// Related Topics 双指针 字符串 动态规划 👍 1381 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [647]-回文子串
 */
public class PalindromicSubstrings {
    public static void main(String[] args) {
        Solution solution = new PalindromicSubstrings().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countSubstrings(String s) {
            int res = 0;

            // dp[i][j] : 在字符串 str 中，第 i - j 字符串是否为回文字符串
            // - str[i] != str[i] 时，肯定不是
            // - str[i] == str[j] 时，取决于 dp[i+1][j-1] 是否回文
            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j - i <= 1 || dp[i + 1][j - 1]) {
                            res++;
                            dp[i][j] = true;
//                            System.out.println("dp[" + i + "][" + j + "]=" + dp[i][j]);
                        }
                    } else {
                        // 啥也不是
                    }
                }
            }

            return res;
        }


        public boolean isPalindromic(String str) {
            if (str == null) return false;
            if (str.isEmpty()) return true;

            int start = 0;
            int end = str.length() - 1;
            while (start < end) {
                if (str.charAt(start++) != str.charAt(end--)) {
                    return false;
                }
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}