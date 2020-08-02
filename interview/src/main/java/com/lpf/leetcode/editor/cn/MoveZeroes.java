//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 675 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [283]-移动零
 */
public class MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
        int[] arr = {0, 1, 0, 3, 12};
        solution.moveZeroes(arr);
        System.out.println(Arrays.toString(arr));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public void moveZeroes(int[] nums) {
            // j为数组中下一个可存放非0数字的位置。（若数组中有0，j在扫过0以后，是第一个为0的位置）
            int j = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[j] = nums[i];
                    if (i != j) {
                        nums[i] = 0;
                    }
                    j++;
                }
            }
        }

        /**
         * 1、先将非零整数都填充到对应位置
         * 2、再将pos及后续所有位置置为0
         */
        public void moveZeroes_A(int[] nums) {
            int pos = 0;

            // 先将非零整数都填充到对应位置
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[pos++] = nums[i];
                }
            }

            // 再将pos及后续所有位置置为0
            for (int i = pos; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}