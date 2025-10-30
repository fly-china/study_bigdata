//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 示例: 
//
// 输入: "25525511135"
//输出: ["255.255.11.135", "255.255.111.35"] 
// Related Topics 字符串 回溯算法
// no.93

package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import netscape.javascript.JSObject;

import java.util.*;

public class RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new RestoreIpAddresses().new Solution();

        String a = "25525511135";
//        String a = "010010";
//        String a = "0000";
//        String a = "101023";

        System.out.println(JSONObject.toJSON(solution.restoreIpAddresses(a)));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<String> res = new ArrayList<>();
        private LinkedList<String> path = new LinkedList<>();

        public List<String> restoreIpAddresses(String s) {
            if (s == null || s.length() < 4) {
                return res;
            }
            backtracking(s, 0);
            return res;
        }

        private void backtracking(String str, int idx) {
            if (path.size() > 4) return;

            if (path.size() == 4 && idx >= str.length()) {
                res.add(buildIpStr(path));
                return;
            }

            for (int i = idx; i < str.length(); i++) {
                String tmpStr = str.substring(idx, i + 1);
                if (isValidIp(tmpStr)) {
                    path.add(tmpStr);
                    backtracking(str, i + 1);
                    path.removeLast();
                } else {
                    break;
                }
            }

        }

        private boolean isValidIp(String str) {
            if (str == null || str.length() > 3) {
                return false;
            }
            if (str.charAt(0) == '0' && str.length() > 1) return false;

            int ipInt = Integer.parseInt(str);
            return ipInt >= 0 && ipInt <= 255;
        }

        private String buildIpStr(List<String> list) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i != list.size() - 1) {
                    sb.append(".");
                }
            }
            return sb.toString();
        }

    }


    //leetcode submit region end(Prohibit modification and deletion)
    // add by 2024/11/18
    class Solution2024 {

        private List<String> res = new ArrayList<>();
        private LinkedList<String> path = new LinkedList<>();

        public List<String> restoreIpAddresses(String s) {
            if (s == null || s.length() < 4) return res;
            backtracking(s, 0);
            return res;
        }

        private void backtracking(String str, int idx) {
            if (path.size() > 4) return;

            if (path.size() == 4 && idx >= str.length()) {
                res.add(buildIpStringByArray(path));
                return;
            }

            for (int i = idx; i < str.length(); i++) {
                String subStr = str.substring(idx, i + 1);
                if (isValidIpPart(subStr)) {
                    path.add(subStr);
                    backtracking(str, i + 1);
                    path.removeLast();
                } else {
                    // 在 131题的“切割回文字符串”中，这里是 continue。原因是：该 part 的字符串不是回文，再往后切割**有可能**是回文的
                    // 而本题中，如果某一 part 不符合 ip 规则，后面肯定不会再符合。所以就也不用管了，直接 break
                    break;
                }
            }

        }

        private boolean isValidIpPart(String str) {
            if (str == null || str.isEmpty()) return false;
            if (str.charAt(0) == '0' && str.length() > 1) return false;

            int num = Integer.parseInt(str);
            return num >= 0 && num <= 255;
        }

        private String buildIpStringByArray(List<String> path) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i != path.size() - 1) {
                    sb.append(".");
                }
            }
            return sb.toString();
        }
    }


    class Solution2 {
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
         * @param counter    计数器
         */
        private void getIpPart(String partResult, List<String> result, String tailStr, int counter) {
            if (counter == 4) {
                if (Objects.isNull(tailStr) || tailStr.length() < 1) {
                    result.add(partResult.substring(1));// 去掉拼接字符串最前面的SPLIT_STR分隔符
                } else {
                    return;
                }
            }


            for (int i = 3; i > 0; i--) {
                if (tailStr.length() < 1) {
                    break;
                }
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
            return Integer.parseInt(str) <= 255;

        }


    }


}