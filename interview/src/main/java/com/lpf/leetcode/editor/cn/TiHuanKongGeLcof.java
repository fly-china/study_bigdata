//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// 👍 44 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [剑指 Offer 05]-替换空格
 */
public class TiHuanKongGeLcof {
    public static void main(String[] args) {
        Solution solution = new TiHuanKongGeLcof().new Solution();
        System.out.println(solution.replaceSpace("We are happy."));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String replaceSpace(String s) {
//            return s == null ? null : s.replace(" ", "%20");

            if (s == null) return null;
            StringBuilder sb = new StringBuilder(s);
            int len1 = s.length() - 1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    // 若存在空格，后面拼装两个空格
                    sb.append("  ");
                }
            }
            int len2 = sb.length() - 1;
            while (len2 > len1 && len1 >= 0) {
                char c = s.charAt(len1--);
                if (c == ' ') {
                    sb.setCharAt(len2--, '0');
                    sb.setCharAt(len2--, '2');
                    sb.setCharAt(len2--, '%');
                } else {
                    sb.setCharAt(len2--, c);
                }
            }

            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}