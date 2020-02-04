//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 示例: 
//
// 输入: "25525511135"
//输出: ["255.255.11.135", "255.255.111.35"] 
// Related Topics 字符串 回溯算法
// no.93

package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new RestoreIpAddresses().new Solution();

//        String a = "25525511135";
        String a = "010010";
//        String a = "1111";
        List<String> list = solution.restoreIpAddresses(a);
        Optional.ofNullable(list)
                .orElse(new ArrayList<>())
                .forEach(System.out::println);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private static final String SPLIT_STR = ".";

        public List<String> restoreIpAddresses(String s) {
            List<String> ipList = new ArrayList<>();
            getIpPart("", ipList, s, 0);
            return ipList;
        }

        /**
         * 递归去生成ip端中每个.之间的部分
         *
         * @param partResult 已生成的部分
         * @param result     全局存放集合
         * @param tailStr    未生成的部分
         * @param counter    技术器
         */
        private void getIpPart(String partResult, List<String> result, String tailStr, int counter) {
            if (counter == 4) {
                if (Objects.isNull(tailStr) || tailStr.length() < 1) {
                    result.add(partResult.substring(1));
                } else {
                    return;
                }
            }


            for (int i = 3; i > 0; i--) {
                if (tailStr.length() < i) {
                    continue;
                }
                String strPart = tailStr.substring(0, i);
                if (checkValidIp(strPart)) {
                    getIpPart(partResult + SPLIT_STR + strPart, result, tailStr.substring(i), counter + 1);
                }
            }
        }

        /**
         * 校验单端ip是否合法
         */
        private boolean checkValidIp(String str) {

            int m = str.length();
            if (m > 3) {
                return false;
            }
            if (str.charAt(0) == '0') {
                return m == 1;
            }
            return Integer.valueOf(str) <= 255;

        }


    }
//leetcode submit region end(Prohibit modification and deletion)


}