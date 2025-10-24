package com.lpf.test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringPermutationCount {
    // 用于存储不同的字符串
    private static Set<String> result = new HashSet<>();

    public static void main(String[] args) {
        String s = "ABAC";

        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);

        System.out.println(new String(charArray));


        int count = countDistinctStrings(s);
        System.out.println(count);
    }


    public static int countDistinctStrings(String s) {
        // 遍历不同长度的子串
        for (int length = 1; length <= s.length(); length++) {
            // 生成指定长度的所有排列
            generatePermutations(s, length, new StringBuilder(), new boolean[s.length()]);
        }
        // 返回不同字符串的数量
        return result.size();
    }

    private static void generatePermutations(String s, int length, StringBuilder current, boolean[] used) {
        // 如果当前字符串达到指定长度，将其添加到结果集合中
        if (current.length() == length) {
            result.add(current.toString());
            return;
        }
        // 遍历字符串中的每个字符
        for (int i = 0; i < s.length(); i++) {
            // 如果该字符未被使用
            if (!used[i]) {
                // 标记该字符已被使用
                used[i] = true;
                // 将该字符添加到当前字符串中
                current.append(s.charAt(i));
                // 递归生成下一个字符的排列
                generatePermutations(s, length, current, used);
                // 回溯，移除最后一个字符
                current.deleteCharAt(current.length() - 1);
                // 标记该字符未被使用
                used[i] = false;
            }
        }
    }
}    