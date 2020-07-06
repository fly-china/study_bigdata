//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 
//
// 说明: 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例: 
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6] 
// Related Topics 数组 双指针


package com.lpf.leetcode.editor.cn;

/**
 * [88]-合并两个有序数组
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new MergeSortedArray().new Solution();
        int[] nums1 = {4, 5, 6, 7, 0, 0, 0};
        int[] nums2 = {1, 2, 3};
        int m = 4;
        int n = 3;
        solution.merge(nums1, m, nums2, n);

        for (int i : nums1) {
            System.out.println(i);
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 两个指针，从大往小填充nums1的数组。
     * 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户 内存消耗:39.3 MB,击败了5.06% 的Java用户
     */
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (nums2 == null) return;
            int i = m + n - 1;
            m--;n--;
            while (n >= 0 || m >= 0){
                if(n < 0) break;
                if(m < 0 || nums1[m] < nums2[n]){
                    nums1[i] = nums2[n];
                    n--;
                }else{
                    nums1[i] = nums1[m];
                    m--;
                }
                i--;
            }

        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 最傻的做法： 执行耗时:1 ms,击败了24.46% 的Java用户 内存消耗:39.7 MB,击败了5.06% 的Java用户
     * 因为从小到大比较，nums1数组中的元素势必要向后移动，最坏的情况时每个元素都要移动N次，效率极差
     */
    class SolutionSlow {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (nums2 == null) return;

            int i1 = 0;
            int i2 = 0;
            while (i2 < nums2.length && i1 < m + i2) {
                if (nums1[i1] <= nums2[i2]) {
                    i1++;
                } else {
                    for (int i = m + i2; i > i1; i--) {
                        nums1[i] = nums1[i - 1];
                    }
                    nums1[i1] = nums2[i2];
                    i2++;
                    i1++;
                }
            }
            if (i2 < nums2.length) {
                for (int i = i2; i < nums2.length; i++, i1++, i2++) {
                    nums1[i1] = nums2[i2];
                }
            }

        }
    }
}