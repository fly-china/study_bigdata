//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 1735 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * [20]-有效的括号
 */
public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        System.out.println(solution.isValid("(){}"));
        System.out.println(solution.isValid("(((((())))))"));
        System.out.println(solution.isValid("()()()()"));
        System.out.println(solution.isValid("(((((((()"));
        System.out.println(solution.isValid("((()(())))"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean isValid(String s) {
            // 用数组代替栈，提升效率
            char[] charstack = new char[s.length()];
            int pointer = -1;
            boolean validFlag = true;
            // 直接将('、'['、'{'的反向括号，放入栈中，便不再需要map
            for (int i = 0; i < s.length(); i++) {
                Character charS = s.charAt(i);
                if (charS.equals('(')) {
                    charstack[++pointer] = ')';
                } else if (charS.equals('[')) {
                    charstack[++pointer] = ']';
                } else if (charS.equals('{')) {
                    charstack[++pointer] = '}';
                } else {
                    // 弹出的栈元素必须和当前")"、"]"、"}"匹配才算有效
                    if (pointer < 0 || charstack[pointer--] != charS) {
                        validFlag = false;
                        break;
                    }
                }
            }

            return validFlag && pointer < 0;
        }


        // 方法二
        public boolean isValid_B(String s) {
            Stack<Character> stack = new Stack<>();
            boolean validFlag = true;
            // 直接将('、'['、'{'的反向括号，放入栈中，便不再需要map
            for (int i = 0; i < s.length(); i++) {
                Character charS = s.charAt(i);
                if (charS.equals('(')) {
                    stack.push(')');
                } else if (charS.equals('[')) {
                    stack.push(']');
                } else if (charS.equals('{')) {
                    stack.push('}');
                } else {
                    // 弹出的栈元素必须和当前")"、"]"、"}"匹配才算有效
                    if (stack.isEmpty() ||stack.pop() != charS) {
                        validFlag = false;
                        break;
                    }
                }
            }

            return validFlag && stack.isEmpty();
        }

        // 方法一
        public boolean isValid_A(String s) {
            if (s == null) return true;
            // map可以考虑优化调。TODO：直接将('、'['、'{'的反向括号，放入栈中，便不再需要map
            Map<Character, Character> map = new HashMap<>();
            map.put(')', '(');
            map.put(']', '[');
            map.put('}', '{');

            Stack<Character> stack = new Stack<>();
            boolean vaildFlag = true;
            for (int i = 0; i < s.length(); i++) {
                Character charS = s.charAt(i);
                if (Objects.equals(charS, '(') || Objects.equals(charS, '[')
                        || Objects.equals(charS, '{')) {
                    stack.push(charS);
                } else {
                    // 弹出的栈元素必须和当前")"、"]"、"}"匹配才算有效
                    if (stack.isEmpty() || stack.pop() != map.get(charS)) {
                        vaildFlag = false;
                        break;
                    }
                }
            }

            // 如果栈中还有元素，说明不匹配
            return vaildFlag && stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}