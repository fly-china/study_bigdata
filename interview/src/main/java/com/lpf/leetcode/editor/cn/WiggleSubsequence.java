//如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也
//视作摆动序列。 
//
// 
// 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。 
// 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一
//个差值为零。 
// 
//
// 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。 
//
// 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,7,4,9,2,5]
//输出：6
//解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,17,5,10,13,15,10,5,16,8]
//输出：7
//解释：这个序列包含几个长度为 7 摆动序列。
//其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3,4,5,6,7,8,9]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 1000 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度完成此题? 
//
// Related Topics 贪心 数组 动态规划 👍 1177 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [376]-摆动序列
 */
public class WiggleSubsequence {
    public static void main(String[] args) {
        Solution solution = new WiggleSubsequence().new Solution();
        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length == 1) {
                return 1;
            }

            int res = 0;
            int preDiff = 0; // 默认在第一个元素前是一个平坡
            int curDiff = 0;
            // 剩下的情况 len >= 3 了，只需要遍历 1 -> len-2 就行
            for (int i = 0; i <= nums.length - 2; i++) {
                curDiff = nums[i + 1] - nums[i];
                // preDiff 有 = 0主要是解决这两种 case： [1,2,2,2,1]  [3,2,2,2,1]
                if ((preDiff >= 0 && curDiff < 0) || (preDiff <= 0 && curDiff > 0)) {
                    res++;
                    preDiff = curDiff;
                }

                // curDiff 就是 i++ 后的  preDiff 了，所以在这赋值后下次循环就不用重新计算 nums[i] - nums[i-1] 了
                // 放在这里的话，每次循环 preDiff 都会变，如果遇到单调坡中有平坡的场景，会导致多算。比如: [1,3,3,4,5], 在 nums[i] = 4 时，多算了一次
//                preDiff = curDiff;
            }


            // 最后元素是一个摆动
            return res + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}