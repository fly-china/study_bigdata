//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 961 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    public void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = solution.letterCombinations(23 + "");
        list.forEach(System.out::println);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // add by 2024.11.14
    class Solution {
        // 数字到号码的映射。使用数组的效率高于JAVA中HashMap
        private String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        private List<String> res = new ArrayList<>();
        private StringBuilder pathSb = new StringBuilder();


        public List<String> letterCombinations(String digits) {
            if (digits != null && !digits.isEmpty()) {
                backtrack(digits, 0);
            }
            return res;
        }

        private void backtrack(String digits, int startIdx) {
            if (startIdx >= digits.length()) {
                res.add(pathSb.toString());
                return;
            }

            String charStr = map[digits.charAt(startIdx) - '0']; // abc
            // 本题每一个数字代表的是不同集合，也就是求不同集合之间的组合，而77. 组合 (opens new window)和216.组合总和III (opens new window)都是求同一个集合中的组合！
            for (int i = 0; i < charStr.length(); i++) {
                pathSb.append(charStr.charAt(i));
                backtrack(digits, startIdx + 1);
                pathSb.deleteCharAt(startIdx);
            }


        }

    }


//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 通俗易懂的理解
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/tong-su-yi-dong-dong-hua-yan-shi-17-dian-hua-hao-m/
     */
    class Solution2 {

        // 数字到号码的映射。使用数组的效率高于JAVA中HashMap
        private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {

            List<String> res = new ArrayList<>();

            if (digits != null && !digits.isEmpty()) {
                backtrack(map, res, digits, 0, new StringBuilder());
            }

            return res;
        }

        // 回溯函数
        private void backtrack(String[] map, List<String> res,
                               String digits, int index, StringBuilder strSb) {
            // terminator
            if (index == digits.length()) {
                res.add(strSb.toString());
                return;
            }

            String a = map[digits.charAt(index) - '2'];
//            String a = map.get(Character.toString(digits.charAt(index)));
            for (int i = 0; i < a.length(); i++) {
                strSb.append(a.charAt(i));
                backtrack(map, res, digits, index + 1, strSb);
                strSb.deleteCharAt(index);// 减枝
            }
        }
    }


}