//给你一个字符串 s，找到 s 中最长的 回文 子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 双指针 字符串 动态规划 👍 7479 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [5]-最长回文子串
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        String str = "abc";
        System.out.println(str.substring(1,3));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // dp[i][j] : str 字符串中 i-j 是否为回文
        public String longestPalindrome(String s) {
            String maxStr = "";

            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j - i <= 1 || dp[i + 1][j - 1]) {
                            dp[i][j] = true;
                            if (maxStr.length() < (j - i + 1)) {
                                maxStr = s.substring(i, j + 1);
                            }
                        }
                    } else {
                        // 啥也不是
                    }
                }
            }

            return maxStr;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}