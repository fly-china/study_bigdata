//给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。 
//
// 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。 
//
// 以数组形式返回答案。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [8,1,2,2,3]
//输出：[4,0,1,1,3]
//解释： 
//对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 
//对于 nums[1]=1 不存在比它小的数字。
//对于 nums[2]=2 存在一个比它小的数字：（1）。 
//对于 nums[3]=2 存在一个比它小的数字：（1）。 
//对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
// 
//
// 示例 2： 
//
// 输入：nums = [6,5,4,8]
//输出：[2,1,0,3]
// 
//
// 示例 3： 
//
// 输入：nums = [7,7,7,7]
//输出：[0,0,0,0]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 500 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 哈希表 
// 👍 88 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [1365]-有多少小于当前数字的数字
 *
 * @author lipengfei
 * @date 2020-10-26 11:32:35
 **/
public class HowManyNumbersAreSmallerThanTheCurrentNumber {
    public static void main(String[] args) {
        Solution solution = new HowManyNumbersAreSmallerThanTheCurrentNumber().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 输入：nums = [8,1,2,2,3]
        // 输出：[4,0,1,1,3]

        /**
         * 计数排序法
         */
        public int[] smallerNumbersThanCurrent(int[] nums) {
            if (nums == null) return new int[0];
            // 记录0-100中每个数出现了多少次（0 <= nums[i] <= 100  是题目中的规定）
            int[] cntArr = new int[101];

            for (int i = 0; i < nums.length; i++) {
                cntArr[nums[i]]++;
            }

            // 记录0-100中每个数,**小于等于**它的累计次数
            for (int i = 1; i < cntArr.length; i++) {
                cntArr[i] += cntArr[i - 1];
            }


            int[] resArr = new int[nums.length];

            for (int i = 0; i < nums.length; i++) {
                //  获取**小于**该数的累计次数,考虑nums[i]=0的特殊情况
                resArr[i] = nums[i] == 0 ? 0 : cntArr[nums[i] - 1];
            }

            return resArr;
        }

        public int[] smallerNumbersThanCurrent_slow(int[] nums) {
            if (nums == null) return new int[0];
            int[] resArr = new int[nums.length];

            for (int i = 0; i < nums.length; i++) {
                int counter = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] < nums[i]) counter++;
                }
                resArr[i] = counter;
            }

            return resArr;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}