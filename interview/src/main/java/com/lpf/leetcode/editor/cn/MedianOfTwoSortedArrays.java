//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。 
//
// 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法

package com.lpf.leetcode.editor.cn;

import java.util.Objects;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        int[] nums1 = {1};
        int[] nums2 = null;
        double medianSortedArrays = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);

    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int nums1Length = Objects.isNull(nums1) ? 0 : nums1.length;
            int nums2Length = Objects.isNull(nums2) ? 0 : nums2.length;
            int totalNum = nums1Length + nums2Length;

            int[] newNum = new int[totalNum];
            int aIndex = 0;
            int bIndex = 0;
            for (int i = 0; i < newNum.length; i++) {
                if (aIndex == nums1Length) {
                    newNum[i] = nums2[bIndex++];
                    continue;
                }
                if (bIndex == nums2Length) {
                    newNum[i] = nums1[aIndex++];
                    continue;
                }

                if (nums1[aIndex] < nums2[bIndex]) {
                    newNum[i] = nums1[aIndex++];
                } else {
                    newNum[i] = nums2[bIndex++];
                }
            }

            // 是否为奇数
            if (totalNum % 2 == 0) {
                return (newNum[totalNum / 2] + newNum[(totalNum / 2) - 1]) / 2d;
            } else {
                return newNum[(totalNum - 1) / 2];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}