//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 
// 👍 585 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new ThreeSumClosest().new Solution();
        int[] nums = {-1, 2, 1, -4};
        int threeSumClosest = solution.threeSumClosest(nums, 1);
        System.out.println(threeSumClosest + "");

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int res = -1;
            int minNum = Integer.MAX_VALUE;

            for (int i = 0; i < nums.length - 2; i++) {

                for (int low = i + 1, high = nums.length - 1; low < high; ) {
                    int currRes = nums[low] + nums[high] + nums[i];
                    if (currRes > target) {
                        high--;
                        if (Math.abs(currRes - target) < minNum) {
                            minNum = Math.abs(currRes - target);
                            res = currRes;
                        }
                        // 移动到下一个不相等的元素
                        while (low < high && nums[high] == nums[high + 1]) high--;
                    } else if (currRes < target) {
                        low++;
                        if (Math.abs(currRes - target) < minNum) {
                            minNum = Math.abs(currRes - target);
                            res = currRes;
                        }
                        // 移动到下一个不相等的元素
                        while (low < high && nums[low] == nums[low - 1]) low++;
                    } else {
                        return currRes;
                    }
                }
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}