//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。 
//
// 示例: 
//
// 输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N
//-1 步，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法 
// 👍 203 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [52]-N皇后 II
 */
public class NQueensIi {
    public static void main(String[] args) {
        Solution solution = new NQueensIi().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int counter = 0;

        public int totalNQueens(int n) {
            if (n <= 1) return n;
            String res = 0 + "";

            if (n > 1) {
                boolean[] colSet = new boolean[n];
                boolean[] pieSet = new boolean[2 * n];
                boolean[] naSet = new boolean[2 * n];


                backtrack(0, n, colSet, pieSet, naSet);
            }

            return counter;
        }


        private void backtrack(int row, int n, boolean[] colSet, boolean[] pieSet, boolean[] naSet) {
            if (row == n) {
                counter++;
                return;
            }

            for (int currCol = 0; currCol < n; currCol++) {
                if (!colSet[currCol] && !pieSet[row + currCol] && !naSet[row - currCol + n]) {
                    colSet[currCol] = true;
                    pieSet[row + currCol] = true;
                    naSet[row - currCol + n] = true;

                    backtrack(row + 1, n, colSet, pieSet, naSet);

                    // 撤销操作
                    colSet[currCol] = false;
                    pieSet[row + currCol] = false;
                    naSet[row - currCol + n] = false;
                }
            }
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution_TRAVERSE {
        int counter = 0;

        public int totalNQueens(int n) {
            if (n <= 1) return n;
            String res = 0 + "";

            if (n > 1) {
                char[][] pos = new char[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        pos[i][j] = '.';
                    }
                }

                backtrack(0, pos);
            }

            return counter;
        }


        private void backtrack(int row, char[][] pos) {
            if (row == pos.length) {
                counter++;
                return;
            }

            for (int col = 0; col < pos[row].length; col++) {
                if (isVaild(col, row, pos)) {
                    //数组复制一份
                    char[][] temp = copy(pos);
                    //在当前位置放个皇后
                    temp[row][col] = 'Q';

                    backtrack(row + 1, temp);

                    // 由于上述方法，每次都是传入的新的char数组，所以不需要撤销操作
                    // temp[row][col] = '.';
                }
            }
        }

        // 判断pos[row][col]位置是否安全合法
        private boolean isVaild(int col, int row, char[][] pos) {
            // 判断同一列上方，是否有Queen
            for (int i = 0; i < row; i++) {
                if (pos[i][col] == 'Q') return false;
            }

            // 判断左上角，是否有Queen
            for (int i = 0; i < row; i++) {

                if (col - row + i < 0) continue;
                if (pos[i][i + (col - row)] == 'Q')
                    return false;
            }

            // 判断右上角，是否有Queen
            for (int i = 0; i < row; i++) {
                if (col + row - i >= pos.length) continue;
                if (pos[i][(col + row) - i] == 'Q')
                    return false;
            }

            return true;
        }

        //把二维数组pos中的数据测下copy一份
        private char[][] copy(char[][] chess) {
            char[][] temp = new char[chess.length][chess[0].length];
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    temp[i][j] = chess[i][j];
                }
            }
            return temp;
        }

    }

}