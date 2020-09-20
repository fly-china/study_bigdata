//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 限制： 
//
// 
// 0 <= matrix.length <= 100 
// 0 <= matrix[i].length <= 100 
// 
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
// Related Topics 数组 
// 👍 132 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [剑指 Offer 29]-顺时针打印矩阵
 */
public class ShunShiZhenDaYinJuZhenLcof {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution solution = new ShunShiZhenDaYinJuZhenLcof().new Solution();
        int[] order = solution.spiralOrder(arr);
        for (int i : order) {
            System.out.print(i + ",");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] spiralOrder(int[][] matrix) {
            if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
                return new int[]{};
            }


            int colLeft = 0, colRight = matrix[0].length - 1;
            int rowsTop = 0, rowsBotom = matrix.length - 1;

            int counter = 0;
            int maxLoopNums = matrix[0].length * matrix.length;

            int[] outArr = new int[maxLoopNums];
            while (counter < maxLoopNums) {
                int index = colLeft;
                while (counter < maxLoopNums && index <= colRight) {
                    outArr[counter++] = matrix[rowsTop][index++];
                }
                rowsTop++;

                index = rowsTop;
                while (counter < maxLoopNums && index <= rowsBotom) {
                    outArr[counter++] = matrix[index++][colRight];
                }
                colRight--;

                index = colRight;
                while (counter < maxLoopNums && index >= colLeft) {
                    outArr[counter++] = matrix[rowsBotom][index--];
                }
                rowsBotom--;

                index = rowsBotom;
                while (counter < maxLoopNums && index >= rowsTop) {
                    outArr[counter++] = matrix[index--][colLeft];
                }
                colLeft++;
            }
            return outArr;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}