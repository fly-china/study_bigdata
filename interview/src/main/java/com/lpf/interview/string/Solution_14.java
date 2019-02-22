package com.lpf.interview.string;

import org.junit.Test;

/**
 * 最长公共前缀
 *
 * @author lipengfei
 * @create 2019-02-21 11:48
 **/
public class Solution_14 {

    @Test
    public void testDemo(){
//        String[] strs = {"dog","racecar","car"};
//        String[] strs = {"flower","flow","flight"};
        String[] strs = {"flower","flow","flooight"};
        String commonPrefix = longestCommonPrefix(strs);
        System.out.println(commonPrefix);

    }

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length < 1)
            return "";
        if(strs.length == 1)
            return strs[0];

        int[] charArr = new int[26];// 存放小写字母出现的个数
        int a_acsii = (int)'a';
        int maxCommonLen = 0;

        int start = 0;
        boolean isBreak = false;
        while(!isBreak){

            for(int i = 0; i < strs.length; i++){
                if(start > strs[i].length()-1){ // 某个字符串已达到最大长度，需要停止遍历
                    isBreak = true;
                    break;
                }

                int char_postion = (int)(strs[i].charAt(start)) - a_acsii; // 坐标：第几个英文字母

                if(charArr[char_postion] % strs.length == i){// 存放相应字母的位置，是否已经有了相应数量的num
                    ++charArr[char_postion];
                }else{// 开始出现不同字母，需要停止遍历
                    isBreak = true;
                    break;
                }
            }

            if(isBreak)
                break;
            start++;
            maxCommonLen++;
        }


        return strs[0].substring(0, maxCommonLen);
    }
}
