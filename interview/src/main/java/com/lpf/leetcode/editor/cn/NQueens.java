//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法 
// 👍 649 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.*;

/**
 * [51]-N 皇后
 */
public class NQueens {
    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
        List<List<String>> lists = solution.solveNQueens(4);
        lists.forEach(System.out::println);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n > 0) {
                // Queen可攻击的范围集合
                boolean[] colSet = new boolean[n];// 列中使用标识
                boolean[] pieSet = new boolean[2 * n];// 副对角线中使用标识。对角线之和会大于n. 范围【0，2n】
                // 主对角线中使用标识。row-col可能会小于0，范围【-n，n】
                // 所以整体+n，即：row-col+n，那么取值范围变为【0，2n】
                boolean[] naSet = new boolean[2 * n];


                char[][] pos = new char[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        pos[i][j] = '.';
                    }
                }

                backtrack(0, n, res, pos, colSet, pieSet, naSet);
            }
            return res;
        }


        private void backtrack(int row, int n, List<List<String>> res, char[][] pos,
                               boolean[] colSet, boolean[] pieSet, boolean[] naSet) {
            if (row == n) {
                List<String> toAdd = new ArrayList<>();
                for (int i = 0; i < n; i ++) {
                    toAdd.add(String.valueOf(pos[i]));
                }
                res.add(toAdd);
                return;
            }

            for (int curCol = 0; curCol < n; curCol++) {
                // naSet中row - curCol还要加n的目的是，为了防止出现负数
                if (!colSet[curCol] && !pieSet[row + curCol] && !naSet[row - curCol + n]) {
                    colSet[curCol] = true;
                    pieSet[row + curCol] = true;
                    naSet[row - curCol + n] = true;
                    pos[row][curCol] = 'Q';

                    backtrack(row + 1, n, res, pos, colSet, pieSet, naSet);

                    // 撤销
                    pos[row][curCol] = '.';
                    colSet[curCol] = false;
                    pieSet[row + curCol] = false;
                    naSet[row - curCol + n] = false;
                }
            }
        }

        private List<String> contructList(Set<Integer> colSet) {
            List<String> list = new ArrayList<>(colSet.size());
            for (Integer integer : colSet) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < colSet.size(); i++) {
                    if (i == integer) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            return list;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution_A {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n > 0) {

                // Queen可攻击的范围集合
                Set<Integer> colSet = new LinkedHashSet<>();// 列集合
                Set<Integer> pieSet = new HashSet<>();// "丿"集合，副对角线集合
                Set<Integer> naSet = new HashSet<>();// "na"集合，主对角线集合


                char[][] pos = new char[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        pos[i][j] = '.';
                    }
                }

                backtrack(0, n, res, colSet, pieSet, naSet);
            }
            return res;
        }


        private void backtrack(int row, int n, List<List<String>> res
                , Set<Integer> colSet, Set<Integer> pieSet, Set<Integer> naSet) {
            if (row == n) {
                res.add(contructList(colSet));
                return;
            }

            for (int curCol = 0; curCol < n; curCol++) {
                if (!colSet.contains(curCol) && !pieSet.contains(row + curCol) && !naSet.contains(row - curCol)) {
                    colSet.add(curCol);
                    pieSet.add(row + curCol);
                    naSet.add(row - curCol);

                    backtrack(row + 1, n, res, colSet, pieSet, naSet);

                    // 撤销
                    colSet.remove(curCol);
                    pieSet.remove(row + curCol);
                    naSet.remove(row - curCol);
                }
            }
        }

        private List<String> contructList(Set<Integer> colSet) {
            List<String> list = new ArrayList<>(colSet.size());
            for (Integer integer : colSet) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < colSet.size(); i++) {
                    if (i == integer) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            return list;
        }


    }

    class Solution_Traverse {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            if (n > 0) {
                char[][] pos = new char[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        pos[i][j] = '.';
                    }
                }

                backtrack(0, pos, res);
            }
            return res;
        }


        private void backtrack(int row, char[][] pos, List<List<String>> res) {
            if (row == pos.length) {
                res.add(contructList(pos));
                return;
            }

            for (int col = 0; col < pos[row].length; col++) {
                if (isVaild(col, row, pos)) {
                    //数组复制一份
                    char[][] temp = copy(pos);
                    //在当前位置放个皇后
                    temp[row][col] = 'Q';

                    backtrack(row + 1, temp, res);

                    // 由于上述方法，每次都是传入的新的char数组，所以不需要撤销操作
                    // temp[row][col] = '.';
                }
            }
        }

        private List<String> contructList(char[][] pos) {
            List<String> list = new ArrayList<>(pos.length);
            for (char[] po : pos) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < po.length; j++) {
                    sb.append(po[j]);
                }
                list.add(sb.toString());
            }
            return list;
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