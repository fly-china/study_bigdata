package com.lpf.testbak;

import java.util.Stack;

public class BracketValidator {

    public static boolean isValid(String expression) {
        // 定义括号的优先级
        java.util.Map<Character, Integer> priority = new java.util.HashMap<>();
        priority.put('(', 1);
        priority.put('[', 2);
        priority.put('{', 3);

        // 定义括号的匹配关系
        java.util.Map<Character, Character> bracketMap = new java.util.HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put(']', '[');
        bracketMap.put('}', '{');

        // 初始化一个栈来存储未匹配的左括号
        Stack<Character> stack = new Stack<>();

        // 遍历表达式中的每个字符
        for (char ch : expression.toCharArray()) {
            // 如果是左括号，直接压入栈中
            if (bracketMap.containsValue(ch)) {
                stack.push(ch);
            }
            // 如果是右括号
            else if (bracketMap.containsKey(ch)) {
                // 如果栈为空，说明没有对应的左括号，直接返回false
                if (stack.isEmpty()) {
                    return false;
                }
                // 弹出栈顶的左括号，检查是否匹配
                char topBracket = stack.pop();
                if (bracketMap.get(ch) != topBracket) {
                    return false;
                }
                // 检查当前括号的优先级是否符合嵌套顺序
                if (!stack.isEmpty() && priority.get(topBracket) > priority.get(stack.peek())) {
                    return false;
                }
            }
            // 如果是其他字符（如数字或操作符），直接跳过
            else {
                continue;
            }
        }

        // 如果栈为空，说明所有括号都正确匹配；否则，有未匹配的左括号
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // 测试用例
//        System.out.println(isValid("()"));  // true
//        System.out.println(isValid("()[]{}"));  // true
//        System.out.println(isValid("(]"));  // false
//        System.out.println(isValid("([)]"));  // false
//        System.out.println(isValid("{[()]}"));  // true
//        System.out.println(isValid("{()}"));  // true
        System.out.println(isValid("[()]"));  // true
        System.out.println(isValid("{{}}[[]](())"));  // true
        System.out.println(isValid("[{}]"));  // false
        System.out.println(isValid("([])"));  // false
        System.out.println(isValid("({})"));  // false
    }
}