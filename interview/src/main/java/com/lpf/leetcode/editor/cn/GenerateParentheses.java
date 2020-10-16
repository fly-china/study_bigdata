//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1320 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
        List<String> list = solution.generateParenthesis(3);
        list.forEach(System.out::println);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            if (n > 0) {
                dfs(n, n, "", res);
            }
            return res;
        }

        private void dfs(int left, int right, String str, List<String> res) {
            // 左括号和右括号都使用完了
            if (left == 0 && right == 0) {
                res.add(str);
                return;
            }

            // 剩余的左括号不能大于右括号，这时相当于需要剪枝
            if (left > right) {
                return;
            }

            // 如果左括号还剩余的话，可以拼接左括号
            if (left > 0) {
                dfs(left - 1, right, str + "(", res);
            }
            // 左括号已经为0。如果右括号还剩余的话，可以拼接右括号
            if (right > 0) {
                dfs(left, right - 1, str + ")", res);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}