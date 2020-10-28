//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 553 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [347]-前 K 个高频元素
 * 
 */
public class TopKFrequentElements{
  public static void main(String[] args) {
	   Solution solution = new TopKFrequentElements().new Solution();
	   
  }
  
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 0 || k > nums.length) {
            return new int[0];
        }


        return nums;
    }

        private void quickSort(int[] nums, int left, int right) {
            if (left >= right) return;

            int partion = partion(nums, left, right);
            quickSort(nums, left, partion - 1);
            quickSort(nums, partion + 1, right);
        }

        private int partion(int[] nums, int left, int right) {
            int pivot = nums[left];
            while (left < right) {
                while (left < right && nums[right] >= pivot) {
                    right--;
                }
                nums[left] = nums[right];

                while (left < right && nums[left] <= pivot) {
                    left++;
                }
                nums[right] = nums[left];
            }

            nums[left] = pivot;
            return left;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

    
}