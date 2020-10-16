//给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。 
//
// 
//
// 示例 1： 
//
// 输入：[-4,-1,0,3,10]
//输出：[0,1,9,16,100]
// 
//
// 示例 2： 
//
// 输入：[-7,-3,2,3,11]
//输出：[4,9,9,49,121]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// -10000 <= A[i] <= 10000 
// A 已按非递减顺序排序。 
// 
// Related Topics 数组 双指针 
// 👍 157 👎 0


package com.lpf.leetcode.editor.cn;

public class SquaresOfASortedArray {
    public static void main(String[] args) {
        Solution solution = new SquaresOfASortedArray().new Solution();
        int[] arr = {-3, -3, -2, 1};
        int[] res = solution.sortedSquares(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 时间复杂度：O(N)
     * 时间复杂度：O(N)
     */
    class Solution {
        public int[] sortedSquares(int[] arr) {
            if (arr == null || arr.length < 1) return arr;

            int[] resArr = new int[arr.length];
            int low = 0;
            int high = arr.length - 1;
            int resIndex = arr.length - 1;

            while (low <= high) {
                if (Math.abs(arr[low]) <= Math.abs(arr[high])) {
                    resArr[resIndex--] = arr[high] * arr[high];
                    high--;
                } else {
                    resArr[resIndex--] = arr[low] * arr[low];
                    low++;
                }
            }
            return resArr;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}