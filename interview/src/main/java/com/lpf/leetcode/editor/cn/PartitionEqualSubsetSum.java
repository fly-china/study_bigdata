//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 2208 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * [416]-分割等和子集
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new PartitionEqualSubsetSum().new Solution();
        Solution2 solution2 = new PartitionEqualSubsetSum().new Solution2();
        int[] nums = {1, 5, 11, 5};
//        System.out.println(solution2.canPartition(nums));
        System.out.println(solution.canPartition(nums));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int currentSum = 0;

        /**
         * a、背包的体积为sum / 2
         * b、背包要放入的商品（集合里的元素）重量为 元素的数值，价值也为元素的数值
         * c、背包如果正好装满，说明找到了总和为 sum / 2 的子集。
         * d、背包中每一个元素是不可重复放入。
         * <p>
         * 1、dp[j]：容量为j的背包，任取 0-i 件商品，所背的物品价值最大可以为dp[j]
         * 2、dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i])
         * 3、初始化：dp[0] = 0;
         * 4、从大到小
         */
        public boolean canPartition(int[] nums) {
            int sum = Arrays.stream(nums).sum();
            int target = sum / 2; // 背包的重量
            if (sum % 2 == 1) // 和为奇数，此题无解
                return false;

            int[] dp = new int[target + 1];
            dp[0] = 0;
            for (int i = 0; i < nums.length; i++) { // 先遍历物品
                for (int j = target; j >= nums[i]; j--) { // 在遍历背包
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                }
                //剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target，優化時間複雜度（26ms -> 20ms）
                if(dp[target] == target)
                    return true;
            }

            return dp[target] == target;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution2 {

        private boolean result = false;
        private LinkedList<Integer> path = new LinkedList<>();
        private int currentSum = 0;

        // 使用回溯法 [1,5,11,5]
        public boolean canPartition(int[] nums) {
            int sum = Arrays.stream(nums).sum();

            if (sum % 2 == 1) // 和为奇数，此题无解
                return false;

            int target = sum / 2;
            Arrays.sort(nums);
            backtracking(sum / 2, nums, 0);

            return result;
        }

        private void backtracking(int target, int[] nums, int startIdx) {
//            if (currentSum == target) {
//                result = true;
//                return;
//            }

            if (path.size() >= nums.length) {
                return;
            }

            for (int i = startIdx; i < nums.length && currentSum < target; i++) {
                path.add(nums[i]);
                currentSum += nums[i];
                if (currentSum == target) {
                    result = true;
                    return;
                }
                backtracking(target, nums, i + 1);
                currentSum -= nums[i];
                path.removeLast();
            }
        }

    }

}