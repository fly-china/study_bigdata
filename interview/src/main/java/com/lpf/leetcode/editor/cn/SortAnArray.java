//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 50000 
// -50000 <= nums[i] <= 50000 
// 
// 👍 175 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [912]-排序数组
 *
 * @author lipengfei
 * @date 2020-10-27 11:12:48
 **/
public class SortAnArray {
    public static void main(String[] args) {
        Solution solution = new SortAnArray().new Solution();
//        int[] arr = {4, 0,0, 5, 7, 7, 9, 3, 0, 6};
        int[] arr = {110, 100, 0};
        arr = solution.sortArray(arr);
        for (int j : arr) {
            System.out.print(j + ", ");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArray(int[] nums) {
            quickSort20241224(nums, 0, nums.length - 1);
            return nums;
        }

        private void quickSort2025(int[] nums, int left, int right) {
            if (nums == null || nums.length <= 1 || right <= left) {
                return;
            }
            boolean ordered = true;
            for (int i = left; i < right-1; i++) {
                if (nums[i] > nums[i+1]) {
                    ordered = false;
                    break;
                }
            }
            if (ordered) return;

            int start = left;
            int end = right;

            int prviot = nums[start];
            while (start < end) {
                while (start < end && nums[end] >= prviot) {
                    end--;
                }
                nums[start] = nums[end];
                while (start < end && nums[start] <= prviot) {
                    start++;
                }
                nums[end] = nums[start];
            }
            nums[start] = prviot;

            quickSort2025(nums, left, start - 1);
            quickSort2025(nums, start + 1, right);
        }



        private void quickSort20241224(int[] nums, int m, int n) {
            if (nums == null || nums.length <= 1 || m >= n) {
                return;
            }

            int start = m;
            int end = n;
            int pivot = nums[start];
            while (start < end) {
                while (start < end && nums[end] > pivot) {
                    end--;
                }
                nums[start] = nums[end];

                while (start < end && nums[start] <= pivot) {
                    start++;
                }
                nums[end] = nums[start];
            }
            nums[start] = pivot;

            quickSort20241224(nums, m, start - 1);
            quickSort20241224(nums, start + 1, n);
        }

        public int[] sortArray2(int[] nums) {
            if (nums == null || nums.length < 1) return new int[0];

            bubbleSort(nums);
            // quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        /**
         * 冒泡排序[5,2,3,1]
         */
        private void bubbleSort(int[] nums) {
            // 从1开始
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < nums.length - i; j++) {
                    if (nums[j] > nums[j + 1]) {
                        swap(nums, j, j + 1);
                    }
                }
            }
        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }


        /**
         * 快速排序
         *
         * @param nums  原数组
         * @param left  左侧边界Index
         * @param right 右侧边界Index
         */
        public void quickSort(int[] nums, int left, int right) {
            if (left >= right) return;

            int partionIndex = partion(nums, left, right);
            quickSort(nums, left, partionIndex - 1);
            quickSort(nums, partionIndex + 1, right);
        }

        private int partion(int[] nums, int left, int right) {
            // 选取数组最左侧的元素为枢纽元素
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