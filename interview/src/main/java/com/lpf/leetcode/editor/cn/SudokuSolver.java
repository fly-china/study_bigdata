//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例 1： 
// 
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
//",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
//","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
//"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
//".",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
//"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
//"4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
//"6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
//"5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
// 
// 
// 
// 
//
//
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
//
// Related Topics 数组 哈希表 回溯 矩阵 👍 1875 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [37]-解数独
 */
public class SudokuSolver {
    public static void main(String[] args) {
        Solution solution = new SudokuSolver().new Solution();
        int a = 1;
        int b = 0;
        int c = 9;
        System.out.println((char) a);
        System.out.println((char) a + '0');

        char aa = 1 + '0';
        char ab = 0;
        System.out.println(aa);
        System.out.println('9' == (char) (9 + '0'));


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solveSudoku(char[][] board) {

            backtracking(board);
            return;
        }


        private boolean backtracking(char[][] board) {

            for (int i = 0; i < board.length; i++) {  // 遍历行
                for (int j = 0; j < board[i].length; j++) {  // 遍历列
                    if (board[i][j] != '.') continue;  // 如果该位置不为空，继续找下一位置

                    // 空位置，尝试放 1-9 之间的数字
                    for (int k = 1; k <= 9; k++) { //
                        if (!isValid(board, i, j, k)) { // 判断是否满足同行、同列、同区域互斥的条件
                            continue;
                        }

                        board[i][j] = (char) (k + '0'); // 赋值
                        boolean result = backtracking(board); // 递归

                        if (result) {  // 如果找到合适一组立刻返回
                            return true;
                        }

                        board[i][j] = '.'; // 撤销
                    }

                    // 9个数都试完了，都不行，那么就返回false
                    // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                    // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
                    return false;
                }
            }

            // 遍历完没有返回false，说明找到了合适棋盘位置了
            return true;
        }

        private boolean isValid(char[][] board, int row, int col, int num) {
            // 同一行只能出现一次
            for (int i = 0; i < board[row].length; i++) {
                if (board[row][i] == (char) (num + '0')) {
                    return false;
                }
            }

            // 同一列只能出现一次
            for (int i = 0; i < board.length; i++) {
                if (board[i][col] == (char) (num + '0')) {
                    return false;
                }
            }

            // 同一 3x3 区域只能出现一次
            int r = row / 3;
            int c = col / 3;
            // (r, c) 代表当前处于第几块区域。  r\c 的范围都是 0-2，即共 9 个区域

            // r = 1 时，  rowIdx >= 1*3 && rowIdx < (1 + 1) * 3
            for (int i = r * 3; i < (r + 1) * 3; i++) {
                for (int j = c * 3; j < (c + 1) * 3; j++) {
                    if (board[i][j] == (char) (num + '0')) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}