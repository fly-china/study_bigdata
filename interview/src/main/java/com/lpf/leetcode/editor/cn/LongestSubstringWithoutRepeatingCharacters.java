//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4020 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [3]-无重复字符的最长子串
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        String str = "abcbbcadbb";
        int length = solution.lengthOfLongestSubstring(str);
        System.out.println(length);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int lengthOfLongestSubstring(String str) {
            if (str == null || str.isEmpty()) return 0;

            Map<Character, Integer> charMap = new HashMap<>(); // <char, startIndex>

            int res = 0;
            int left = 0, right = 0;
            while (right < str.length()) {
                char rightChar = str.charAt(right);
                if (charMap.containsKey(rightChar)) {
                    left = Math.max(left, charMap.get(rightChar) + 1); // 移动到首个重复字母之后
                }
                res = Math.max(res, right - left + 1);
                charMap.put(rightChar, right);
                right++;
            }

            return res;
        }


        // abcabcbb
        public int lengthOfLongestSubstring2020(String str) {
            if (str == null || str.length() == 0) return 0;
            int maxLength = 0;
            // map中key是字符，value是字符出现的位置
            int[] arrMap = new int[256];
//            Map<Character, Integer> map = new HashMap<>(str.length());
            // abcbbcadbb
            for (int low = 0, high = 0; high < str.length(); high++) {
                // 如果发现重复字符串，将低位直接定位到，最后一次出现重复的位置
                low = Math.max(low, arrMap[str.charAt(high)]);
                maxLength = Math.max(maxLength, high - low + 1);
                arrMap[str.charAt(high)] = high + 1;
            }

            return maxLength;
        }
    }

    class Solution_Map {
        // abcabcbb
        public int lengthOfLongestSubstring(String str) {
            if (str == null || str.length() == 0) return 0;
            int maxLength = 0;
            // map中key是字符，value是字符出现的位置
            Map<Character, Integer> map = new HashMap<>(str.length());
            // abcbbcadbb
            for (int low = 0, high = 0; high < str.length(); high++) {
                // 如果发现重复字符串，将低位直接定位到，最后一次出现重复的位置
                if (map.containsKey(str.charAt(high))) {
                    low = Math.max(low, map.get(str.charAt(high)));
                }
                maxLength = Math.max(maxLength, high - low + 1);
                map.put(str.charAt(high), high + 1);
            }

            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}