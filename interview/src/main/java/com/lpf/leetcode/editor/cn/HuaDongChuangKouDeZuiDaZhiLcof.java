//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/ 
// Related Topics 栈 Sliding Window


package com.lpf.leetcode.editor.cn;

/**
 * [面试题59 - I]-滑动窗口的最大值
 */
public class HuaDongChuangKouDeZuiDaZhiLcof{
  public static void main(String[] args) {
	   Solution solution = new HuaDongChuangKouDeZuiDaZhiLcof().new Solution();
	   
  }
  
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || k > nums.length){
            return null;
        }
        // 1、先找出前k的最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
             if(nums[i] > max){
                 max = nums[i];
             }
        }

        // 2、
        int[] maxArr = new int[nums.length - k];
        maxArr[0] = max;
//        Stack<Integer> stack

        return null;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
}