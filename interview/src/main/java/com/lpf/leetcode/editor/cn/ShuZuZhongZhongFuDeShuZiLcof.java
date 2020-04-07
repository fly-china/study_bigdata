//找出数组中重复的数字。 
//
// 
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。 
//
// 示例 1： 
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3 
// 
//
// 
//
// 限制： 
//
// 2 <= n <= 100000 
// Related Topics 数组 哈希表


package com.lpf.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [面试题03]-数组中重复的数字
 * 方法一：利用hash寻址
 * 方法二：利用原地置换，置换后要求nums[i] = i,如果置换时，发现temp = nums[i]，即代表出现数字重复
 */
public class ShuZuZhongZhongFuDeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new ShuZuZhongZhongFuDeShuZiLcof().new Solution();
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(solution.findRepeatNumber(nums));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findRepeatNumber(int[] nums) {
            int repeateInt = -1;
            if (nums == null || nums.length < 2)
                return repeateInt;

            for (int i = 0; i < nums.length; i++) {
                // 不断的置换直到达到数组的每个位置均达到num[i] = i
                while (nums[i] != i) {
                    int temp = nums[i];
                    if (nums[temp] == temp) {
                        // 如果欲要设置的位置，已经存在 值 == 数组角标，说明该数已经存在
                        repeateInt = temp;
                        break;
                    }
                    nums[temp] = nums[i];
                    nums[i] = temp;
                }
                if (repeateInt != -1) {
                    break;
                }
            }
            return repeateInt;
        }
    }


    class Solution_hash {
        public int findRepeatNumber(int[] nums) {
            int repeateInt = -1;
            if (nums == null || nums.length < 2)
                return repeateInt;


            Map<Integer, Integer> repeateMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (repeateMap.get(nums[i]) == null) {
                    repeateMap.put(nums[i], -1);
                } else {
                    repeateInt = nums[i];
                    break;
                }
            }

            return repeateInt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}