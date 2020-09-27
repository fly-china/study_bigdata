//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针 
// 👍 574 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static void main(String[] args) {
        Solution solution = new FourSum().new Solution();
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
//        int[] nums = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> lists = solution.fourSum(nums, 0);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null) return result;
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 3; i++) {
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                threeSum(nums, i, target - nums[i], result);
            }

            return result;
        }

        //{-2, -1, 0, 0, 1, 2}
        private void threeSum(int[] nums, int beginIndex, int target, List<List<Integer>> result) {
            int ori = nums[beginIndex];

            for (int i = beginIndex + 1; i < nums.length - 2; i++) {
                // 防止重复
                if (i > beginIndex + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }

                for (int low = i + 1, high = nums.length - 1; low < high; ) {
                    int sum = nums[high] + nums[low] + nums[i];
                    if (sum == target) {
                        List<Integer> partResult = new ArrayList<>();
                        partResult.add(ori);
                        partResult.add(nums[i]);
                        partResult.add(nums[low]);
                        partResult.add(nums[high]);
                        result.add(partResult);
                        high--;
                        low++;
                        while (low < high && high < nums.length - 1 && nums[high] == nums[high + 1]) high--;
                        while (low < high && low > i + 1 && nums[low] == nums[low - 1]) low++;
                        continue;
                    } else if (sum > target) {
                        high--;
                        while (low < high && high < nums.length - 1 && nums[high] == nums[high + 1]) high--;
                    } else {
                        low++;
                        while (low < high && low > i + 1 && nums[low] == nums[low - 1]) low++;
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}