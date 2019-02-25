package com.lpf.leetcode.stack;

import org.junit.Test;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author lipengfei
 * @create 2019-02-20 21:49
 **/
public class Solution_3 {

    @Test
    public void testSolution() {
//        int maxLength = lengthOfLongestSubstring("abcabcbb");
//        int maxLength = lengthOfLongestSubstring("pwwkew");
        int maxLength = lengthOfLongestSubstring("pwabcwkew");
//        int maxLength = lengthOfLongestSubstring("bbbbb");
        System.out.println(maxLength);

    }


    public int lengthOfLongestSubstring(String s) {

        int start = 0;
        int end = 0;
        int maxLength = 0;
//        int a_acsii = (int) 'a';

        int[] str = new int[256];// 26位分别存放对应的26个英文字符（0：不存在|1:存在）

        int endCharPos;
        int startCharPos;
        while (end < s.length()) {
            endCharPos = s.charAt(end);


            if (str[endCharPos] < 1) { // 0,不存在重复元素

                maxLength = Math.max(maxLength, (end - start + 1));
                str[endCharPos] = str[endCharPos] + 1;
                end++;

//            }else if(endCharPos >= 1){
            } else {
                str[endCharPos] = str[endCharPos] + 1;
                while (isRepe(str)) {
                    startCharPos = s.charAt(start);
                    str[startCharPos] = str[startCharPos] - 1;
                    start++;
                }
                end++;
            }


        }


        return maxLength;
    }


    public boolean isRepe(int[] strArr) {
        boolean flag = false;
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] > 1) {
                flag = true;
                break;
            }
        }

        return flag;
    }
}
