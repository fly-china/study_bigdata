//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
//判断数组中是否含有该整数。 
//
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// [
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
//
// 
//
// 限制： 
//
// 0 <= n <= 1000 
//
// 0 <= m <= 1000 
//
// 
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/ 
// Related Topics 数组 双指针 
// 👍 117 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * 数组从左到右和从上到下都是升序的，如果从右上角出发开始遍历呢？
 * 会发现每次都是向左数字会变小，向下数字会变大，有点和二分查找树相似。二分查找树的话，是向左数字变小，向右数字变大。
 * <p>
 * [剑指 Offer 04]-二维数组中的查找
 */
public class ErWeiShuZuZhongDeChaZhaoLcof {
    public static void main(String[] args) {
        Solution solution = new ErWeiShuZuZhongDeChaZhaoLcof().new Solution();

//        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int[][] matrix = {{}, {}};
        int target = 5; // 20
        System.out.println(solution.findNumberIn2DArray(matrix, target));


    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 从右上角开始遍历。
         * target比当前值大，则行号+1；
         * target比当前值小，则列号-1；
         */
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;

            int row = 0;
            int column = matrix[0].length - 1;
            while (column >= 0 && row < matrix.length) {
                if (target == matrix[row][column]) {
                    return true;
                } else if (target > matrix[row][column]) {
                    row++;
                } else {
                    column--;
                }
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}