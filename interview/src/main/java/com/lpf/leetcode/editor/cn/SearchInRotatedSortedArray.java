//整数数组 nums 按升序排列，数组中的值 互不相同 。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 二分查找 👍 3074 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [33]-搜索旋转排序数组
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArray().new Solution();
        int[] nums = {1, 3};
        int search = solution.search(nums, 3);
        System.out.println(search);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int search(int[] nums, int target) {
            return searchByHalfSearch(nums, target);
        }

        // 二分查找，时间复杂度 O(lgn)
        public int searchByHalfSearch(int[] nums, int target) {
            int low = 0;
            int high = nums.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (nums[mid] == target) {
                    return mid;
                }

                // [4,5,6,7,8, 9, -1,0,1,2,3]
                if (nums[mid] >= nums[low]) { // 中间值比左侧大。 mid 在左段。
                    if (target < nums[mid] && target >= nums[low]) {  // target 比 mid 小，但是比 low 要大，那 target 就在左侧区域
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else { // 中间值比左侧大。 mid 在右段。
                    if (target > nums[mid] && target <= nums[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }

            }

            return -1;
        }


        /**
         * 平均时间复杂度O(n/2)，不满足题意
         */
        public int searchByIter(int[] nums, int target) {
            if (nums[0] > target) {
                int high = nums.length - 1;
                while (high > 0 && (high == nums.length - 1
                        || nums[high] < nums[high + 1])) {
                    if (nums[high] == target) return high;
                    high--;
                }
            } else if (nums[0] < target) {
                int low = 0;
                while (low < nums.length && (low == 0
                        || nums[low] > nums[low - 1])) {
                    if (nums[low] == target) return low;
                    low++;
                }
            } else {
                return 0;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}