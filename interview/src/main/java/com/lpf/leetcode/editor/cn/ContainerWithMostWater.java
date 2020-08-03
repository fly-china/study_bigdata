//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
// Related Topics 数组 双指针 
// 👍 1689 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [11]-盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 暴力法：双重for循环计算每个面积 --O(n^2)
         * 双指针法：从两端向内部缩
         */
        public int maxArea(int[] height) {
            int low = 0;
            int high = height.length - 1;
            int maxArea = 0;

            while (low < high) {
                //谁小，就计算出当前的最小值，同时将小的那个收拢
                int h = height[low] < height[high] ? height[low++] : height[high--];
                //这里要 j-i+1 是因为此时 j 或者 i 已经收拢了，不是真正的上面高度对应的宽度
                maxArea = Math.max(maxArea, h * (high - low + 1));
            }

//            while (low < high) {
//                int newArea = (high - low) * Math.min(height[low], height[high]);
//                maxArea = Math.max(maxArea, newArea);
//
//                // 谁小谁动
//                if (height[low] < height[high]) {
//                    low++;
//                } else {
//                    high--;
//                }
//            }

            return maxArea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}