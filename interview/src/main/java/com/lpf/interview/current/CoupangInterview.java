package com.lpf.interview.current;

import java.util.EnumSet;
import java.util.Stack;

/**
 * 加减乘除
 *
 * Given a an expression string, calculate the result of the expression.
 * Consider addition and subtraction with brackets
 * <p>
 * for example:
 * Input: "1 + 2 - 3 + 5", output: 5
 * Input: "-2+(5 - 3)", output: 0
 **/
public class CoupangInterview {

    public static void main(String[] args) {
        System.out.println(calc("1 + 2 - 3 + 5")); // 5
        System.out.println(calc("-3-(5-3)")); // -5
        System.out.println(calc("(3-2)-(2-3)")); // 2
        System.out.println(calc("((2-3)-(2-(3-4)))")); // -4


        System.out.println(calc("1+2*3-6/2")); // 4
    }

    private static int calc(String expl) {
        return calcFunc2(expl);
    }

    private static int calcFunc2(String expl) {
        if (expl == null) return Integer.MIN_VALUE;

        expl = expl.replaceAll(" ", "");

        Stack<Integer> stack = new Stack<>();   // 计算数
        Stack<Character> opStack = new Stack<>();  // + - * /
        int num = 0; //

        for (int i = 0; i < expl.length(); i++) {
            char c = expl.charAt(i);

            if (Character.isDigit(c)) {
                num = c - '0'; // 1
                stack.push(num);
            } else if (c == '(') {
                opStack.push(c);
            } else if (c == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    int tmpNum = calcByStack(stack, opStack);
                    stack.push(tmpNum);
                }
                opStack.pop(); // 弹出 '('
            } else if (c == '*' || c == '/' || c == '+' || c == '-') {
                if (i == 0 && c == '-') {
                    stack.push(0); // 压入0处理负数
                    opStack.push('-'); // 压入0处理负数
                    continue;
                }

                // 1+2*3-6/2
                while (!opStack.isEmpty() && getOpLevel(opStack.peek()) >= getOpLevel(c)) {
                    int tmpNum = calcByStack(stack, opStack);
                    stack.push(tmpNum);
                }
                opStack.push(c);
            } else {
                throw new RuntimeException("invalid char");
            }

        }

        while (!opStack.isEmpty()) {
            int tmpNum = calcByStack(stack, opStack);
            stack.push(tmpNum);
        }

        return stack.pop();
    }

    private static int getOpLevel(Character op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '/':
            case '*':
                return 2;
        }
        return 0;
    }

    // 1+2*3*4-6/2"
    // stack： 1  2  3
    // opstack: + *  -

    private static int calcByStack(Stack<Integer> numStack, Stack<Character> opStack) {
        Character op = opStack.pop();
        Integer num2 = numStack.pop();
        Integer num1 = numStack.pop();

        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '/':
                return num1 / num2;
            case '*':
                return num1 * num2;
        }

        return Integer.MIN_VALUE;
    }

    private static int calcFunc1(String expl) {
        if (expl == null) return Integer.MIN_VALUE;

        expl = expl.replaceAll(" ", "");

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int signRes = 1; // 符号数。 +1 代表前面是 + ，-1 代表前面是-
        int num = 0; //

        for (int i = 0; i < expl.length(); i++) {
            char c = expl.charAt(i);

            if (Character.isDigit(c)) {
                num = c - '0'; // 1
            } else if (c == '+') {
                res += signRes * num;
                num = 0;
                signRes = 1;
            } else if (c == '-') {
                res += signRes * num;
                num = 0;
                signRes = -1;
            } else if (c == '(') {
                stack.push(res);        // 报存左括号前的符号
                stack.push(signRes);    // 保存临时结果，处理完括号内部后，需要重新 pop 出
                signRes = 1;
                res = 0;
            } else if (c == ')') {
                res += signRes * num;
                num = 0;

                Integer tmpSign = stack.pop();  // 左括号前的符号
                Integer tmpRes = stack.pop();
                res = tmpRes + tmpSign * res;
            } else if (c == '*' || c == '/') {
                // 1+2*3-6/2

                // TODO: 处理乘除
            } else {
                throw new RuntimeException("invalid char");
            }

        }

        res += signRes * num;

        return res;
    }
}
