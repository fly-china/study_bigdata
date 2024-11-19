//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 1889 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * [131]-分割回文串
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        Solution solution = new PalindromePartitioning().new Solution();
        System.out.println(JSONObject.toJSON(solution.partition("acdc")));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<List<String>> res = new ArrayList<>();
        private LinkedList<String> path = new LinkedList<>();

        public List<List<String>> partition(String s) {
            if (s == null || s.isEmpty()) return res;
            backtracking(s, 0);
            return res;
        }

        private void backtracking(String str, int idx) {
            if (idx >= str.length()) {
                res.add(new ArrayList<>(path));
                return;
            }


            for (int i = idx; i < str.length(); i++) {
                String substring = str.substring(idx, (i + 1));
                if (!isPalindromic(substring)) {
                    continue;
                }
                path.add(substring);
                backtracking(str, i + 1);
                path.removeLast();
            }


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