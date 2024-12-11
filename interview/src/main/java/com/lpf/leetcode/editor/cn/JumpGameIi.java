//给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。 
//
// 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处: 
//
// 
// 0 <= j <= nums[i] 
// i + j < n 
// 
//
// 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 示例 2: 
//
// 
//输入: nums = [2,3,0,1,4]
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 1000 
// 题目保证可以到达 nums[n-1] 
// 
//
// Related Topics 贪心 数组 动态规划 👍 2679 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [45]-跳跃游戏 II
 */
public class JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new JumpGameIi().new Solution();
        System.out.println(solution.jump(new int[]{2, 3, 1, 1, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        /**
         * 贪心：覆盖区域法
         * [4,6,2,1,5,1,1,1,1,5,1,1,1,1,1] 这个例子可以很好的解释
         */
        public int jump(int[] nums) {

            if (nums.length <= 1) return 0;
            int step = 0; // 步数
            int curCover = 0; // 当前这一跳能够覆盖到的最大范围
            int nextCover = 0; // 下一跳能够覆盖到的最大范围
            int i = 0;
            while (i < nums.length) {
                // 不断遍历，寻找下一跳能到达的最大距离，有则更新。
                nextCover = Math.max(nextCover, i + nums[i]);

                // 当达到本一跳的最大范围后，更新 cur 为 next，同时增加一步
                if (i == curCover) {
                    curCover = nextCover;
                    step++;
                }

                // 当前这第一步已经能覆盖结尾，直接返回
                if (curCover >= nums.length - 1) {
                    break;
                }

                i++;
            }

            return step;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}