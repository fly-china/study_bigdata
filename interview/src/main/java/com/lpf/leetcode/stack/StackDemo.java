package com.lpf.leetcode.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * @author lipengfei
 * @create 2019-02-16 12:37
 **/
public class StackDemo {

    @Test
    public void checkInclude() {

        String s1 = "baf";
        String s2 = "dddddabfcc";
        System.out.println(checkInclusion(s1, s2));

    }


    // 输入的字符串只包含小写字母
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length())
            return false;

        int aASCII = (int) 'a';

        /**
         * 差异数组,存放两个字符串所有26小写字母的个数差异
         * 数组中均为0，代表相同。
         */
        int[] diff = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            int aArrIndex = (int) (s1.charAt(i)) - aASCII;
            int bArrIndex = (int) (s2.charAt(i)) - aASCII;

            diff[aArrIndex] = ++diff[aArrIndex];
            diff[bArrIndex] = --diff[bArrIndex];
        }

        boolean flag = false;
        for (int i = s1.length(); i < s2.length(); i++) {
            // 1、先判断diff数组是否均为0
            if (isSame(diff)) {
                flag = true;
                break;
            }

            // 2、滑动s2窗口(吐出原首字母，添加原尾部字母的后一个字母)
            int oriIndex = (int) (s2.charAt(i - s1.length() )) - aASCII;
            int newTailIndex = (int) (s2.charAt(i)) - aASCII;

            diff[oriIndex] = ++diff[oriIndex];
            diff[newTailIndex] = --diff[newTailIndex];
        }


        return flag == true ? true : isSame(diff);
    }


    public boolean isSame(int[] diff) {

        for (int j = 0; j < diff.length; j++) {
            if (diff[j] != 0) {// 存在差异
                return false;
            }
        }
        return true;
    }


    @Test
    public void stackApply() {

        boolean b = backspaceCompare("ab#c", "ad#c");
        System.out.println(b);


    }


    public boolean backspaceCompare(String S, String T) {


//        Stack<Character> stackS = strToCharStack(S);
//        Stack<Character> stackT = strToCharStack(T);

//        String strS = stackToString(stackS);
//        String strT = stackToString(stackT);

        Stack<Character> ss = new Stack<>();
        Stack<Character> ts = new Stack<>();


        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != '#')
                ss.push(S.charAt(i));
            else if (!ss.isEmpty())
                ss.pop();
        }

        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) != '#')
                ts.push(T.charAt(i));
            else if (!ts.isEmpty())
                ts.pop();
        }

        return ss.equals(ts);
    }


    public Stack<Character> strToCharStack(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < chars.length; i++) {
            stack.push(chars[i]);
        }

        return stack;
    }

    public String stackToString(Stack<Character> stack) {
        Stack<Character> newStack = new Stack<>();

        int backNums = 0;
        while (stack != null && stack.size() > 0) {
            Character pop = stack.pop();

            if (pop.equals('#')) {
                backNums++;
            } else {
                if (backNums > 0) {
                    backNums--;
                } else {
                    newStack.push(pop);
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        while (newStack.size() > 0) {
            sb.append(newStack.pop());
        }

        return sb.toString();
    }


    @Test
    public void testBaseAPI() {

        Stack<String> stack = new Stack<String>();

        stack.push("a");
        stack.push("b");
        stack.push("c");


        int search = stack.search("b");
        System.out.println(search);

        // 显示栈顶元素，但不出栈
        String peek1 = stack.peek();
        String peek2 = stack.peek();
        System.out.println(peek1);
        System.out.println(peek2);

        // 从栈中弹出元素
        String pop1 = stack.pop();
        String pop2 = stack.pop();
        System.out.println(pop1);
        System.out.println(pop2);

        Character aa = new Character('#');
        System.out.println(aa.equals('#'));

    }
}
