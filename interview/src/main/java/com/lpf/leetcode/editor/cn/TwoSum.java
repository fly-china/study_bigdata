//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表

package com.lpf.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
       int[] nums = {2, 7, 11, 15};
       int  target = 9;

        Solution solution = new TwoSum().new Solution();
        int[] twoSum = solution.twoSum(nums, target);
        System.out.println("第一位index=" + twoSum[0] + "--值value=" + nums[0]);
        System.out.println("第二位index=" + twoSum[1] + "--值value=" + nums[1]);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] result = new int[2];

            Map<Integer, Integer> map = new HashMap<>(nums.length);
            for (int i = 0; i < nums.length; i++) {
                int currTargetNum = target - nums[i];
                if (map.containsKey(currTargetNum)) {
                    result[0] = map.get(currTargetNum);
                    result[1] = i;
                    break;
                }
                map.put(nums[i], i);
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}