//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 示例 1: 
//
// 输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 说明: 
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。 
// Related Topics 堆 分治算法 
// 👍 761 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.*;

/**
 * [215]-数组中的第K个最大元素
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new KthLargestElementInAnArray().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int findKthLargest(int[] nums, int k) {
            List<Integer> list = new ArrayList<>();
            Collections.addAll(list, Arrays.stream(nums).boxed().toArray(Integer[]::new));



            return findKNumRecur(list, k);
        }


        private int findKNumRecur(List<Integer> nums,  int k) {
            if (nums.size() < 1 || nums.size() < k) {
                return Integer.MIN_VALUE;
            }

            // 将大于、小于、等于 pivot 的元素划分至 big, small, equal 中
            List<Integer> big = new ArrayList<>();
            List<Integer> equal = new ArrayList<>();
            List<Integer> small = new ArrayList<>();

            Random random = new Random();
            int randomInt = random.nextInt(nums.size());
            int prviot = nums.get(randomInt);

            for (Integer num : nums) {
                if (num > prviot) {
                    big.add(num);
                } else if (num < prviot) {
                    small.add(num);
                } else {
                    equal.add(num);
                }
            }

            if (k <= big.size()) {
                return findKNumRecur(big, k);
            } else if (k > big.size() && k <= (big.size() + equal.size()) ) {
                return equal.get(0);
            } else {
                return findKNumRecur(small, k - (big.size() + equal.size()));
            }
        }


        public int findKthLargest2024(int[] nums, int k) {
            if (nums == null || nums.length < 1 || k < 0 || k > nums.length) {
                return -1;
            }
            // 借助快排实现，先排序完再找TopK。
            // 但全部排序完，太慢了。找到某一个pivot=k即可
            quickSortHelper(nums, 0, nums.length - 1, k);

            return nums[nums.length - k];
        }

        private int quickSortHelper(int[] nums, int left, int right, int k) {
            if (left >= right) return -1;
            int partionIdx = partion(nums, left, right);
            if (partionIdx == (nums.length - k)) {
                return nums[partionIdx];
            }

            return partionIdx > (nums.length - k) ? quickSortHelper(nums, left, partionIdx - 1, k) :
                    quickSortHelper(nums, partionIdx + 1, right, k);

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

    class Solution_SLOW {
        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length < 1 || k < 0 || k > nums.length) {
                return -1;
            }
            //  借助快排实现，先排序完再找TopK。
            quickSort(nums, 0, nums.length - 1);

            return nums[nums.length - k];
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

}