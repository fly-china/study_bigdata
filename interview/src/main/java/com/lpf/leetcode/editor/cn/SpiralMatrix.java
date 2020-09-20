//给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。 
//
// 示例 1: 
//
// 输入:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//输出: [1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2: 
//
// 输入:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//输出: [1,2,3,4,8,12,11,10,9,5,6,7]
// 
// Related Topics 数组 
// 👍 493 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * [54]-螺旋矩阵
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrix().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
                return new ArrayList<>(0);
            }


            int colLeft = 0, colRight = matrix[0].length - 1;
            int rowsTop = 0, rowsBotom = matrix.length - 1;

            int counter = 0;
            int maxLoopNums = matrix[0].length * matrix.length;

            List<Integer> outArrList = new ArrayList<>(maxLoopNums);
            while (counter < maxLoopNums) {
                int index = colLeft;
                while (counter < maxLoopNums && index <= colRight) {
                    outArrList.add(matrix[rowsTop][index++]);
                    counter++;
                }
                rowsTop++;

                index = rowsTop;
                while (counter < maxLoopNums && index <= rowsBotom) {
                    outArrList.add(matrix[index++][colRight]);
                    counter++;
                }
                colRight--;

                index = colRight;
                while (counter < maxLoopNums && index >= colLeft) {
                    outArrList.add(matrix[rowsBotom][index--]);
                    counter++;
                }
                rowsBotom--;

                index = rowsBotom;
                while (counter < maxLoopNums && index >= rowsTop) {
                    outArrList.add(matrix[index--][colLeft]);
                    counter++;
                }
                colLeft++;
            }
            return outArrList;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}