//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。 
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 1283 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [516]-最长回文子序列
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestPalindromeSubseq(String s) {

            // dp[i][j] : str 中 i-j 的最长回文子序列的长度
            int[][] dp = new int[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = 1;
            }


            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i + 1; j < s.length(); j++) {
                    int len = 0;
                    if (s.charAt(i) == s.charAt(j)) {
                        len = dp[i + 1][j - 1] + 2;
                    } else {
                        len = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                    dp[i][j] = len;
//                    System.out.println("dp[" + i + "][" + j + "]=" + dp[i][j]);
                }
            }

            return dp[0][s.length() - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}